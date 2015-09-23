package com.vilyever.filereadwrite;

import android.os.Environment;

import com.vilyever.contextholder.VDContextHolder;

import java.io.File;

/**
 * VDFileConstant
 * AndroidFileReadWrite <com.vilyever.filereadwrite>
 * Created by vilyever on 2015/9/22.
 * Feature:
 */
public class VDFileConstant {
    private final VDFileConstant self = this;

    
    /* #Constructors */    
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */    
    
    /* #Public Methods */
    public static File getCacheDir() {
        return getCacheDir(null);
    }

    public static File getCacheDir(String subDirName) {
        File dir;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            dir = VDContextHolder.getContext().getExternalCacheDir();
        }
        else {
            dir = VDContextHolder.getContext().getCacheDir();
        }

        if (subDirName != null) {
            File subDir = new File(dir.getPath() + "/" + subDirName);
            if (VDFileWriter.createDir(subDir)) {
                dir = subDir;
            }
            else {
                dir = null;
            }
        }

        return dir;
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}