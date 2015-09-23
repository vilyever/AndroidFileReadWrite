package com.vilyever.filereadwrite;

import android.graphics.Bitmap;
import android.util.Base64;

import com.vilyever.contextholder.VDContextHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * VDFileWriter
 * AndroidFileReadWrite <com.vilyever.filereadwrite>
 * Created by vilyever on 2015/9/22.
 * Feature:
 */
public class VDFileWriter {
    private final VDFileWriter self = this;

    
    /* #Constructors */    
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */
    private static boolean deleteDir(File dir) {
        if (!clearDir(dir)) {
            return false;
        }
        return dir.delete();
    }
    
    /* #Public Methods */
    public static void clearCache() {
        try {
            File dir = VDContextHolder.getContext().getCacheDir();
            if (dir != null && dir.isDirectory()) {
                clearDir(dir, false);
            }

            dir = VDContextHolder.getContext().getExternalCacheDir();
            if (dir != null && dir.isDirectory()) {
                clearDir(dir, false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean clearDir(File dir) {
        return clearDir(dir, false);
    }

    public static boolean clearDir(File dir, boolean deleteDir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        if (deleteDir) {
            return deleteDir(dir);
        }

        return true;
    }

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

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}