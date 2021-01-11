package me.javapacker.config;

import me.javapacker.steps.api.PackerStep;

import java.io.*;
import java.util.Properties;

public class PackerConfiguration {

    public static PackerConfiguration INSTANCE = new PackerConfiguration();
    final Properties properties = new Properties();
    private static String encryptionKey;
    private static String inputPath;
    private static String outputPath;
    private static boolean useNullByteName;
    private static boolean fakeDirectory;

   private PackerConfiguration() { }

    public void readConfiguration() throws Exception {
        final File f = new File("javapacker.properties");
        if(!f.exists())
        {
            properties.setProperty("inputPath", "input.jar");
            properties.setProperty("outputPath", "output.jar");
            properties.setProperty("encryptionKey", "ABCDEFGHIJKLMNPA");
            properties.setProperty("useNullByteName", "true");
            properties.setProperty("fakeDirectory", "true");
            properties.store(new FileOutputStream(f), "");
        }
        loadConfiguration(f);
    }

    private void loadConfiguration(final File f) throws IOException {
        properties.load(new FileInputStream(f));
        inputPath = properties.getProperty("inputPath");
        outputPath = properties.getProperty("outputPath");
        encryptionKey = properties.getProperty("encryptionKey");
        useNullByteName = Boolean.parseBoolean(properties.getProperty("useNullByteName"));
        fakeDirectory = Boolean.parseBoolean(properties.getProperty("fakeDirectory"));
        if(encryptionKey.length() != 16)
        {
            throw new RuntimeException("EncryptionKey length must be 16 bytes!");
        }
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public boolean isUseNullByteName() {
        return useNullByteName;
    }

    public boolean isFakeDirectory() {
        return fakeDirectory;
    }
}

