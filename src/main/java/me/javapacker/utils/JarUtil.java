package me.javapacker.utils;

import java.io.File;

public class JarUtil {

    public static File getCurrentFile()
    {
        return new File(JarUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("file:", ""));
    }
}
