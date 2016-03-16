package com.vilyever.filereadwrite;

import android.os.Environment;
import android.support.annotation.Nullable;

import com.vilyever.contextholder.ContextHolder;

import java.io.File;

/**
 * FileConstant
 * AndroidFileReadWrite <com.vilyever.filereadwrite>
 * Created by vilyever on 2015/9/22.
 * Feature:
 */
public class FileConstant {
    private final FileConstant self = this;

    /* Public Methods */
    /** @see #getCacheDir(String) */
    public static File getCacheDir() {
        return getCacheDir(null);
    }

    /**
     * 获取缓存文件夹
     * @param subDirName 缓存文件夹下子文件夹，若不存在自动创建
     * @return 指定的缓存文件夹
     */
    @Nullable
    public static File getCacheDir(String subDirName) {
        File dir;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            dir = ContextHolder.getContext().getExternalCacheDir();
        }
        else {
            dir = ContextHolder.getContext().getCacheDir();
        }

        if (dir == null) {
            return null;
        }

        if (subDirName != null) {
            File subDir = new File(dir.getAbsolutePath() + "/" + subDirName);
            if (FileWriter.createDir(subDir)) {
                dir = subDir;
            }
            else {
                dir = null;
            }
        }

        return dir;
    }
}