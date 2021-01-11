package me.javapacker.steps.impl;

import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.steps.api.PackerStep;
import me.javapacker.utils.StringUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class S03TrashingResource extends PackerStep {


    public S03TrashingResource(PackerFilesManager packerFilesManager) {
        super(packerFilesManager);
    }

    public boolean completeStep() {
        try {
            ZipOutputStream zos = getPackerFilesManager().asZipOutputStream();
            for (int i = 0; i < 100; i++) {
                final ZipEntry entry = new ZipEntry(StringUtil.randomPackerName(30));
                final byte[] trashByte = new byte[Files.readAllBytes(Paths.get(getPackerFilesManager().getInputFile().getFile().getAbsolutePath())).length - 1];
                zos.putNextEntry(entry);
                SecureRandom.getInstanceStrong().nextBytes(trashByte);
                zos.write(trashByte);
            }
        }
        catch (Exception e)
        {
            logMessage(Level.WARNING, "S03TrashingResource failed!");
            e.printStackTrace();
            return false;
        }
        logMessage(Level.INFO, "S03TrashingResource completed!");
        return checkNext();
    }
}
