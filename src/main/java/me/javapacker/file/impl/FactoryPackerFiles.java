package me.javapacker.file.impl;

import me.javapacker.file.api.PackerFilesManager;

import java.io.File;

public class FactoryPackerFiles {

    public static PackerFilesManager createPackerFilesManager(final String inputPath, final String outputPath)
    {
        return new PackerFilesManagerImpl(new PackerFileImpl(new File(inputPath), true),
                new PackerFileImpl(new File(outputPath), false)
                );
    }

}
