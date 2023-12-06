package org.ixkit.land.io;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

public class Resources {
    public static String loadBaseClass(Class<?> contextClass, String shortFileName){

        String buf = null;
        try {

            String fileName = shortFileName;
            InputStream ins =  contextClass.getResourceAsStream(fileName);
            if (null == ins){

                return null;
            }
            buf =   IOUtils.toString(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  buf;
    }

    public static String load(String fileName){

        String buf = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream ins =  classLoader.getResourceAsStream(fileName);
            if (null == ins){

                return "";
            }

            // buf = Resources.toString(ins, Charsets.UTF_8);
            buf =   IOUtils.toString(ins, Charsets.UTF_8.name());
        } catch (IOException e) {

            e.printStackTrace();
        }
        return  buf;
    }

    private static String loadSELF(String fileName){

        try {
            return com.google.common.io.Resources.toString(com.google.common.io.Resources.getResource(fileName), Charsets.UTF_8);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    private static String getFileAbsolutePath(String shortPath) {
        Resources helper = new Resources();
        URL res = helper.getClass().getClassLoader().getResource(shortPath);

        File file;
        try {
            if (null == res){
                return  null;
            }
            file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            return absolutePath;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return shortPath;

    }

    public static File extractFile(String jarFilePath)
    {
        String fileName = ResourceManager.extract(jarFilePath);
        File result = new File(fileName);
        return result;
    }

}
