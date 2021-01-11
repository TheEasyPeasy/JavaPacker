package me.javapacker.file.api;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public interface PackerFile {

    File getFile();

    default JarFile asJarFile() throws IOException {
        return new JarFile(getFile());
    }

    String getMainClassName();


}
