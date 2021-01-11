package me.javapacker.file.impl;

import me.javapacker.file.api.Encryptable;

import java.util.Random;

public class EncryptableImpl implements Encryptable {

    private String encryptableConfig;
    private String encryptableFileName;
    private final String encryptionKey;
    private final byte[] encryptionIV;

    public EncryptableImpl(String encryptionKey) {
        this.encryptionKey = encryptionKey;
        final byte[] iv = new byte[16];
        new Random().nextBytes(iv);
        this.encryptionIV = iv;
    }

    @Override
    public String getEncryptableConfig() {
        return this.encryptableConfig;
    }

    @Override
    public String getEncryptableFile() {
        return this.encryptableFileName;
    }

    @Override
    public void setEncryptableConfigName(String name) {
        this.encryptableConfig = name;
    }

    @Override
    public void setEncryptableFileName(String name) {
        this.encryptableFileName = name;
    }

    @Override
    public String getEncryptionKey() {
        return this.encryptionKey;
    }

    @Override
    public byte[] getEncryptionIV() {
        return this.encryptionIV;
    }
}
