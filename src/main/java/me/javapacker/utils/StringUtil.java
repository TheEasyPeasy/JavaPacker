package me.javapacker.utils;

import me.javapacker.config.PackerConfiguration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class StringUtil {

    private final static String fileExtension = ".javapacker";
    private final static Set<String> usedNames = new HashSet<>();

    public static String randomPackerName(int length){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<length; i++){
            int random = RandomUtil.fromRange(0, 1);
            s.append(random == 1 ? "I" : "i");
        }
        if(usedNames.contains(s.toString()))
        {
            return randomPackerName(length);
        }
        usedNames.add(s.toString());
        if(PackerConfiguration.INSTANCE.isFakeDirectory())
        {
            s.append("/"); //Thanks to https://github.com/x4e/fakedirectory
        }
        if(PackerConfiguration.INSTANCE.isUseNullByteName())
        {
            s.insert(0, "\u0000");
        }
        return s.toString();
    }

}
