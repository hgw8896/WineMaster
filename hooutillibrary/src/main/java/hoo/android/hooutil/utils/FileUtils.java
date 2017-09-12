package hoo.android.hooutil.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by camera360 on 15/2/26.
 */
public class FileUtils {

    public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String APP_PATH = SDCARD_ROOT + File.separator + "PandaSki";
    public static final String APP_VOICE_TEMP = APP_PATH + File.separator + "VoiceTemp";
    public static final String APP_VOICE = APP_PATH + File.separator + "Voice";
    public static final String APP_SOURCE = APP_PATH + File.separator + ".source";
    public static final String APP_VIDEO = APP_PATH + File.separator + "Video";
    private static final int BUFFER_SIZE = 8 * 1024;
    public static final String APP_ZIP = ".zip";

    /*
     * 拷贝单个文件
     *
     * @param srcPath  源文件
     * @param destPath 目标文件
     */
    public static void copySingleFile(String srcPath, String destPath) throws IOException {
        if (srcPath == null || destPath == null) {
            throw new IOException("path is Null, srcPath=" + srcPath + ",destPath=" + destPath);
        }
        copySingleFile(new File(srcPath), new File(destPath));
    }

    /*
     * 拷贝单个文件（不管目标文件是否存在，都会创建一个空的文件）
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     */
    public static void copySingleFile(File srcFile, File destFile) throws IOException {
        File parent = destFile.getParentFile();
        if (!checkFolder(parent)) {
            throw new IOException("Create Folder(" + parent.getAbsolutePath() + ") Failed!");
        }

        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFile));
            BufferedOutputStream out = null;
            try {
                // 不管目标文件是否存在，都会创建一个空的文件！
                out = new BufferedOutputStream(new FileOutputStream(destFile));
                byte[] buffer = new byte[BUFFER_SIZE];
                int len = -1;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            } finally {
                close(out);
            }
        } finally {
            close(in);
        }
    }

    /*
     * 将文件转换为byte[] 数据.
     *
     * @param filePath 文件路径
     * @return byte[] 文件数据
     * @throws java.io.IOException
     */
    public static byte[] getFileData(String filePath) throws IOException {
        return getFileData(new File(filePath));
    }

    /*
     * 将文件转换为byte[] 数据.
     *
     * @param file 文件
     * @return byte[] 文件数据
     * @throws java.io.IOException
     */
    public static byte[] getFileData(File file) throws IOException {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            return getStreamData(in);
        } finally {
            close(in);
        }
    }

    /*
     * 文件夹检查，不存在则新建
     *
     * @param folderPath 文件夹检查，不存在则新建
     * @return true，存在或新建成功，false，不存在或新建失败
     */
    public static boolean checkFolder(String folderPath) {
        if (TextUtils.isEmpty(folderPath)) {
            return false;
        }
        return checkFolder(new File(folderPath));
    }

    /*
     * 文件夹检查，不存在则新建
     *
     * @param folder 文件夹检查，不存在则新建
     * @return true，存在或新建成功，false，不存在或新建失败
     */
    public static boolean checkFolder(File folder) {
        if (folder == null) {
            return false;
        }

        if (folder.isDirectory()) {
            return true;
        }

        return folder.mkdirs();
    }

    /*
     * 文件检查
     */
    public static boolean checkFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        return checkFile(new File(path));
    }

    /*
     * 文件检查
     */
    public static boolean checkFile(File file) {
        if (file == null) {
            return false;
        }
        return file.exists();
    }

    /*
     * 取得文件内容
     *
     * @param file 文件
     * @return 文件
     * @throws Exception 读取异常
     */
    public static String getFileContent(File file) throws IOException {
        BufferedReader in = null;
        long fileSize = file.length();
        if (fileSize > Short.MAX_VALUE) {
            fileSize = Short.MAX_VALUE;
        }
        StringBuilder sb = new StringBuilder((int) fileSize);
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
        }

        return sb.toString();
    }

    /*
     * 将字符串写入文件
     *
     * @param file    写入的文件
     * @param content 文件内容
     * @throws Exception 异常
     */
    public static void writeFileContent(File file, String content) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        try {
            out.write(content.getBytes("utf-8"));
            out.flush();
        } finally {
            close(out);
        }
    }

    public static void deleteFile(String path) {
        if (null == path || "".equals(path)) {
            return;
        }

        File file = new File(path);
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        if (null == file || !file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            deleteFile(file.listFiles());
        } else {
            file.delete();
        }
    }

    public static void deleteFile(File[] files) {
        if (null == files || files.length == 0) {
            return;
        }

        for (File file : files) {
            deleteFile(file);
        }
    }


    public static void close(InputStream in) throws IOException {
        if (in != null) {
            in.close();
            in = null;
        }
    }

    public static void close(OutputStream out) throws IOException {
        if (out != null) {
            out.close();
            out = null;
        }
    }

    /*
     * 返回文本文件行数
     *
     * @param file
     */
    public static int getLineNumber(File file) {
        LineNumberReader lnr = null;
        int count = 0;
        try {
            lnr = new LineNumberReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = null;
            while ((line = lnr.readLine()) != null) {
                count += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != lnr) {
                    lnr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return count;
        }
    }

    /*
     * 保存Bitmap
     * @return boolean
     */
    public static boolean saveBitmap(String path, Bitmap bitmap, int quality, Bitmap.CompressFormat format) throws IOException, IllegalArgumentException {
        if (TextUtils.isEmpty(path) || bitmap == null) {
            return false;
        }
        boolean flag = false;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            if (bitmap.compress(format, quality, out)) {
                out.flush();
                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(out);
        }

        return flag;
    }

    /*
     * 保存文件
     * @param data 二进制数据文件
     * @param path 路径
     */
    public static void saveFile(byte[] data, String path) throws IOException {
        if (data == null) {
            throw new IOException("data is null");
        }
        if (path == null) {
            throw new IOException("path is null");
        }
        File parent = new File(path).getParentFile();
        if (!checkFolder(parent)) {
            throw new IOException("Create Folder(" + parent.getAbsolutePath() + ") Failed!");
        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(path));
            out.write(data);
        } finally {
            close(out);
        }
    }


    public static byte[] getStreamData(InputStream in) throws IOException {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            return out.toByteArray();
        } finally {
            close(out);
        }
    }

    public static ArrayList<String> findAllFiles(File dir) {
        ArrayList<String> list = new ArrayList<>();
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (int i = 0; i < fs.length; i++) {
                if (fs[i].isFile()) {
                    list.add(fs[i].getPath());
                }
            }
        }
        Collections.sort(list);
        return list;
    }


}
