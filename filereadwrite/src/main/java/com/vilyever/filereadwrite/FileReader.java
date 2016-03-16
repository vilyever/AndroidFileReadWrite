package com.vilyever.filereadwrite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Base64OutputStream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * FileReader
 * AndroidFileReadWrite <com.vilyever.filereadwrite>
 * Created by vilyever on 2015/9/22.
 * Feature:
 */
public class FileReader {
    private final FileReader self = this;

    /* Public Methods */
    /**
     * 从文件中读取字符串
     * @param file 指定文件
     * @return 读取的字符串
     */
    public static String readText(File file) {
        if (!file.exists()) {
            return null;
        }

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file.getAbsolutePath());

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            return stringBuilder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 从指定文件读取Base64编码
     * @param file 指定文件
     * @return Base64编码
     */
    public static String readBase64(File file) {
        if (!file.exists()) {
            return null;
        }

        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        Base64OutputStream output64 = null;

        try {
            inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] buffer = new byte[8192];
            int bytesRead;
            outputStream = new ByteArrayOutputStream();
            output64 = new Base64OutputStream(outputStream, Base64.DEFAULT);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            return outputStream.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (output64 != null) {
                    output64.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 从指定文件读取bitmap图片
     * @param file 指定文件
     * @return bitmap图片
     */
    public static Bitmap readBitmap(File file) {
        if (!file.exists()) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        return bitmap;
    }
}