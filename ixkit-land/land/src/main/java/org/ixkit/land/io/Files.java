package org.ixkit.land.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Function;

public class Files {
    private static String sp = File.separator;

    public static String toPath(String ... items){
        StringBuffer result = new StringBuffer();
        for (int i = 0; i <items.length ; i++) {
            String item = items[i];
            result.append(item);
            if (i +1 < items.length){
                if (!StringUtils.startsWith(sp,items[i + 1])) {
                    result.append(sp);
                }
            }
        }
        return result.toString();
    }
    public static boolean readyFileFolder(String fullPathFileName){
        File file = new File(fullPathFileName);
        try {
            com.google.common.io.Files.createParentDirs(file);
            return true;
        } catch (IOException e) {
           // throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }
    public static boolean readyDirectory(String folderName){
        File file = new File(folderName);
        try {

            if (file.exists()) return true;

            folderName  = folderName +   File.separator + "temp";
            file = new File( folderName);
            com.google.common.io.Files.createParentDirs(file);

            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }finally {

        }
        return false;
    }


    public static  void read(String  fileName, int maxOnReadSize, Function<String,Boolean> onRead) throws IOException {
        try (SeekableByteChannel ch = java.nio.file.Files.newByteChannel(Paths.get(fileName), StandardOpenOption.READ)) {
            ByteBuffer bf = ByteBuffer.allocate(maxOnReadSize);
            int size = ch.read(bf);
            while ( size> 0) {
                bf.flip();

                byte[] bytes = new byte[size];
                bf.get(bytes);
                //bf.get(bytes,0,size);
                String buf = new String(bytes);
                boolean go = (Boolean) onRead.apply(buf);
                if (!go) {
                    break;
                }
                bf.clear();
                //next
                size = ch.read(bf);
            }
        }
    }

    public static String load(String fileName){
        try {
            return    FileUtils.readFileToString( new File(fileName), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static long _fileSize(String fileName){
        File file = new File(fileName);
        long size = file.length();
        return size;
    }
    public static long fileSize(String fileName){
        try {
            Path path = Paths.get(fileName);
            FileChannel fileChannel = FileChannel.open(path);
            long size = fileChannel.size();
            long nextSize = _fileSize(fileName);
            if (size != nextSize){
                size = nextSize;
            }
           return fileChannel.size();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static boolean isExist(String fileName){
        Path path = Path.of(fileName);
        try {
            return java.nio.file.Files.exists(path);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
