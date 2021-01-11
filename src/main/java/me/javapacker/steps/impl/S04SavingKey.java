package me.javapacker.steps.impl;

import me.javapacker.config.PackerConfiguration;
import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.steps.api.PackerStep;
import me.javapacker.utils.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class S04SavingKey extends PackerStep {


    public S04SavingKey(PackerFilesManager packerFilesManager) {
        super(packerFilesManager);
    }

    public boolean completeStep() {
        final ZipOutputStream zos = getPackerFilesManager().asZipOutputStream();
        try {
            final String configName = StringUtil.randomPackerName(30);
            getPackerFilesManager().getEncryptable().setEncryptableConfigName(configName);
            final ZipEntry entry = new ZipEntry(configName);
            zos.putNextEntry(entry);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(bos);
            dos.writeUTF(getPackerFilesManager().getInputFile().getMainClassName());
            dos.writeUTF(PackerConfiguration.INSTANCE.getEncryptionKey());
            zos.write(bos.toByteArray());
        }catch (Exception e)
        {
            logMessage(Level.WARNING, "S04SavingKey failed!");
            e.printStackTrace();
            return false;
        }
        logMessage(Level.INFO, "S04SavingKey completed!");
        return checkNext();
    }
}
