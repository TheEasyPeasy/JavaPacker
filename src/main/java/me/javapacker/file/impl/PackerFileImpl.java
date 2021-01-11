package me.javapacker.file.impl;

import me.javapacker.file.api.PackerFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.jar.JarFile;

public class PackerFileImpl implements PackerFile {

    private final File file;
    private String mainClassName;

    public PackerFileImpl(File file, boolean readMain) {
        this.file = file;
        if(!readMain)
        {
            return;
        }
        try {
            final JarFile jarFile = asJarFile();
            Map<Object, Object> map = jarFile.getManifest().getMainAttributes();
            for (Object obj : map.keySet()) {
                if (obj.toString().equalsIgnoreCase("main-class")) {
                    mainClassName = map.get(obj).toString();
                    break;
                }
            }
            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public String getMainClassName() {
        return this.mainClassName;
    }
}
