package me.javapacker.file.impl;

import me.javapacker.config.PackerConfiguration;
import me.javapacker.file.api.Encryptable;
import me.javapacker.file.api.PackerFile;
import me.javapacker.file.api.PackerFilesManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.zip.ZipOutputStream;

public class PackerFilesManagerImpl implements PackerFilesManager {

    private final PackerFile inputFile;
    private final PackerFile outputFile;
    private ZipOutputStream zipOutputStream;
    private final Encryptable encryptable;

    public PackerFilesManagerImpl(PackerFile inputFile, PackerFile outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        try {
            this.zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.encryptable = new EncryptableImpl(PackerConfiguration.INSTANCE.getEncryptionKey());
    }

    @Override
    public PackerFile getInputFile() {
        return this.inputFile;
    }

    @Override
    public PackerFile getOutputFile() {
        return this.outputFile;
    }

    @Override
    public ZipOutputStream asZipOutputStream() {
        return this.zipOutputStream;
    }

    @Override
    public Encryptable getEncryptable() {
        return this.encryptable;
    }


}
