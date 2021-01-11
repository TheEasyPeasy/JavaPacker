package me.javapacker.steps.impl;

import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.steps.api.PackerStep;
import me.javapacker.utils.StringUtil;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class S02EncryptClasses extends PackerStep {


    public S02EncryptClasses(PackerFilesManager packerFilesManager) {
        super(packerFilesManager);
    }

    public boolean completeStep() {

        Cipher cipher = null;
        try {
            ZipOutputStream out = getPackerFilesManager().asZipOutputStream();

            cipher = Cipher.getInstance("AES/CBC/NOPADDING");

            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getPackerFilesManager().getEncryptable().getEncryptionKey().getBytes(), "AES"), new IvParameterSpec(getPackerFilesManager().getEncryptable().getEncryptionKey().getBytes()));

            FileInputStream is = new FileInputStream(getPackerFilesManager().getInputFile().getFile());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CipherOutputStream cos = new CipherOutputStream(baos, cipher);

            IOUtils.copy(is, cos);

            is.close();
            cos.close();

            final String jarData = StringUtil.randomPackerName(30);
            getPackerFilesManager().getEncryptable().setEncryptableFileName(jarData);
            ZipEntry entry = new ZipEntry(jarData);
            out.putNextEntry(entry);
            out.write(baos.toByteArray());
            out.closeEntry();

        }
        catch (Exception e)
        {
            logMessage(Level.WARNING, "S02EncryptClasses failed!");
            e.printStackTrace();
            return false;
        }
        logMessage(Level.INFO, "S02EncryptClasses completed!");
        return checkNext();
    }


}
