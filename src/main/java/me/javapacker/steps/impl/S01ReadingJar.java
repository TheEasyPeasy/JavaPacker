package me.javapacker.steps.impl;

import me.javapacker.config.PackerConfiguration;
import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.steps.api.PackerStep;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class S01ReadingJar extends PackerStep {


    public S01ReadingJar(PackerFilesManager packerFilesManager) {
        super(packerFilesManager);
    }

    public boolean completeStep()
    {
        final File f = new File(PackerConfiguration.INSTANCE.getInputPath());
        if(!f.exists())
        {
            logMessage(Level.WARNING, "File input.jar does not exists!");
            return false;
        }
        try {
            final ZipFile zipFile = new ZipFile(f);
            ZipOutputStream out = getPackerFilesManager().asZipOutputStream();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry entry = entries.nextElement();
                if (!entry.getName().toLowerCase().contains("meta-inf") && !entry.getName().toLowerCase().endsWith(".class")) {
                    byte[] b = IOUtils.toByteArray(zipFile.getInputStream(entry));
                    out.putNextEntry(entry);
                    out.write(b);
                    out.closeEntry();
                }
            }
            zipFile.close();
        }
        catch (Exception e)
        {
            logMessage(Level.WARNING, "S01ReadingJar failed!");
            e.printStackTrace();
            return false;
        }
        logMessage(Level.INFO, "S01ReadingJar completed!");
        return checkNext();
    }
}
