package me.javapacker.steps.impl;

import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.steps.api.PackerStep;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class S06AttachManifest extends PackerStep {

    public S06AttachManifest(PackerFilesManager packerFilesManager) {
        super(packerFilesManager);
    }

    @Override
    public boolean completeStep() {
        final ZipOutputStream out = getPackerFilesManager().asZipOutputStream();
        try {
            URL resource = getClass().getClassLoader().getResource("MANIFEST.MF");
            if (resource == null) {
                logMessage(Level.WARNING, "MANIFEST.MF not found!");
                return false;
            } else {

                final File manifest = new File(resource.toURI());
                ZipEntry entry = new ZipEntry("META-INF/" + manifest.getName());
                out.putNextEntry(entry);
                out.write(IOUtils.toByteArray(new FileInputStream(manifest)));
                out.closeEntry();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        logMessage(Level.INFO, "S06AttachManifest completed!");
        return checkNext();
    }
}
