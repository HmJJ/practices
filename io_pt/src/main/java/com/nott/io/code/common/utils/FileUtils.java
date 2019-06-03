package com.nott.io.code.common.utils;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;


public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static void saveToFile(byte[] bytes, String path) {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        write(inputStream, path);
    }

    public static boolean createDirectory(String path) {
        boolean result = false;
        File folder = new File(path);
        if (!folder.exists()) {
            result =folder.mkdir();
        }
        return result;
    }

    public static void write(String content, String path) {
        OutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(content.getBytes("utf-8"));
            os = new FileOutputStream(path);
            FileCopyUtils.copy(is, os);
        } catch (IOException e) {
            log.error(ExceptionUtils.getErrorInfo(e));
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    public static void write(InputStream is, String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            FileCopyUtils.copy(is, os);
        } catch (IOException e) {
            log.error(ExceptionUtils.getErrorInfo(e));
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else {
            return null;
        }
    }

    public static void deleteFile(String path) {

        File file = getFile(path);

        if (file != null) {
            file.delete();
        }
    }

    public static InputStream getFileInputStream(String path) {

        FileSystemResource resource = new FileSystemResource(path);

        try {
            return resource.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] copyToByteArray(File file) {

        try {
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
