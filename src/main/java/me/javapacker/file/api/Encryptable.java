package me.javapacker.file.api;

public interface Encryptable {

    String getEncryptableConfig();

    String getEncryptableFile();

    void setEncryptableConfigName(final String name);

    void setEncryptableFileName(final String name);

    String getEncryptionKey();

    byte[] getEncryptionIV();

}
