package me.javapacker.file.api;

import java.util.zip.ZipOutputStream;

public interface PackerFilesManager {

    PackerFile getInputFile();

    PackerFile getOutputFile();

    ZipOutputStream asZipOutputStream();

    Encryptable getEncryptable();

}
