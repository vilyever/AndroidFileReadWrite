package com.vilyever.filereadwrite;

import android.graphics.Bitmap;
import android.util.Base64;

import com.vilyever.contextholder.ContextHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileWriter
 * AndroidFileReadWrite <com.vilyever.filereadwrite>
 * Created by vilyever on 2015/9/22.
 * Feature:
 */
public class FileWriter {
    private final FileWriter self = this;

    /* Public Methods */
    /**
     * 清空缓存文件夹
     */
    public static void clearCache() {
        try {
            File dir = ContextHolder.getContext().getCacheDir();
            if (dir != null && dir.isDirectory()) {
                clearDir(dir, false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File dir = ContextHolder.getContext().getExternalCacheDir();
            if (dir != null && dir.isDirectory()) {
                clearDir(dir, false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** @see #clearDir(File, boolean) */
    public static boolean clearDir(File dir) {
        return clearDir(dir, false);
    }

    /**
     * 清空指定文件夹
     * @param dir 指定文件夹
     * @param deleteDir 是否清空后删除指定文件夹
     * @return 是否成否清空
     */
    public static boolean clearDir(File dir, boolean deleteDir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }

            if (deleteDir) {
                return deleteDir(dir);
            }
        }

        return true;
    }

    /**
     * 创建指定文件夹
     * @param dir 指定文件夹
     * @return 是否创建成功
     */
    public static boolean createDir(File dir) {
        if (dir == null) {
            return false;
        }

        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        else {
            return dir.mkdirs();
        }
    }

    /**
     * 向指定文件写入字符串
     * @param file 指定文件
     * @param text 要写入的字符串
     * @return 是否写入成功
     */
    public static boolean writeText(File file, String text) {
        FileOutputStream outputStream = null;

        try {
            if (file.exists()) {
                file.delete();
            }

            outputStream = new FileOutputStream(file.getAbsolutePath(), true);
            outputStream.write(text.getBytes());
            outputStream.flush();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 向指定文件写入Base64
     * @param file 指定文件
     * @param base64 要写入的Base64
     * @return 是否写入成功
     */
    public static boolean writeBase64String(File file, String base64) {
        FileOutputStream outputStream = null;
        try {
            if (file.exists()) {
                file.delete();
            }

            outputStream = new FileOutputStream(file.getAbsolutePath(), true);
            outputStream.write(Base64.decode(base64, Base64.DEFAULT));
            outputStream.flush();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 向指定文件写入bitmap
     * @param file 指定文件
     * @param bitmap 要写入的bitmap
     * @return 是否写入成功
     */
    public static boolean writeBitmap(File file, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file.getAbsolutePath());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
            outputStream.flush();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /* Private Methods */
    /**
     * 删除文件夹
     * @param dir 指定文件夹
     * @return 是否删除成功
     */
    private static boolean deleteDir(File dir) {
        if (!clearDir(dir)) {
            return false;
        }

        if (dir != null) {
            return dir.delete();
        }

        return true;
    }
}