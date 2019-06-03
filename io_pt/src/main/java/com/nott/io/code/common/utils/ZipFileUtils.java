package com.nott.io.code.common.utils;

import com.wayonsys.common.exception.ServiceException;
import com.wayonsys.common.json.JacksonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/20 15:21
 * @Modified By:
 */
public class ZipFileUtils {

    private static final Logger log = LoggerFactory.getLogger(ZipFileUtils.class);


    public static byte[] zipPath(String path) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zos = new ZipOutputStream(bufferedOutputStream);
        File file = new File(path);
        try {
            zipFile(zos, file,"");
            zos.finish();
            zos.flush();
        } catch (IOException e) {
            log.error("parameter:{}, error trace is :{}", path, ExceptionUtils.getErrorInfo(e));
            throw new ServiceException(e.getMessage());
        }
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);
        IOUtils.closeQuietly(zos);
        return byteArrayOutputStream.toByteArray();
    }


    private static void zipFile(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws IOException {
        if (fileToZip == null || !fileToZip.exists()) {
            return;
        }
        String zipEntryName = fileToZip.getName();
        if (StringUtils.isNotBlank(parrentDirectoryName)) {
            zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
        }

        if (fileToZip.isDirectory()) {
            System.out.println("+" + zipEntryName);
            zos.putNextEntry(new ZipEntry(zipEntryName + "/"));
            zos.closeEntry();
            for (File file : fileToZip.listFiles()) {
                zipFile(zos, file, fileToZip.getName());
            }
        } else {
            log.info("   " + zipEntryName);
            zos.putNextEntry(new ZipEntry(zipEntryName));
            FileInputStream fis = new FileInputStream(fileToZip);
            IOUtils.copy(fis, zos);
            zos.closeEntry();
            fis.close();
        }
    }


    public static byte[] zipFiles(List<String> files) {

        if (files.size() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zip = new ZipOutputStream(bufferedOutputStream);
        try {

            for(String path : files) {
                File file = new File(path);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zip.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file);
                IOUtils.copy(fileInputStream, zip);
                fileInputStream.close();
                zip.closeEntry();
            }

            zip.finish();
            zip.flush();
            IOUtils.closeQuietly(bufferedOutputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            log.error("message is :{}, parameter:{}", e.getMessage(), JacksonUtils.toJSON(files));
            throw new ServiceException(e.getMessage());
        } catch (IOException e) {
            log.error("message is :{}, parameter:{}", e.getMessage(), JacksonUtils.toJSON(files));
            throw new ServiceException(e.getMessage());
        }finally {
            IOUtils.closeQuietly(zip);
        }

    }

}
