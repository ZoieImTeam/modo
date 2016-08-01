package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2016/1/7.
 */
public class MyUtils {

    /**
     * 压缩图片  长宽选最大的按比例缩放
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    private static Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        // 获得图片宽高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);

        //再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }


    /**
     * 得到缩放的大小
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int hiegh = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || hiegh > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(hiegh * 1.0f / reqHeight);
            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

    /**
     * 将Bitmap转化成File
     *
     * @param bitmap
     * @param path
     */
    public static void saveBitmapFile(Bitmap bitmap, String path) {
        File file = new File(path);//将要保存图片的路径
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 把相册或相机拍下照片的本地路径传入，即可压缩并且返回压缩后的路径
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static String saveBitmapFile(String path, int width, int height) {

        setDownloadPath();

        Bitmap bitmap = decodeSampledBitmapFromPath(path, width, height);
        long time = System.currentTimeMillis();
        String save_path = Environment.getExternalStorageDirectory().getPath() + com.binvshe.binvshe.binvsheui.chen.Utils.Constants.ZANPLUSAPP_PATH_DOWNLOAD + time + ".jpg";
        File file = new File(save_path);//将要保存图片的路径
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            return save_path;
        } catch (IOException e) {
            e.printStackTrace();
            return path;
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        return path;
    }

    /**
     * 把相册或相机拍下照片的本地路径传入，即可压缩并且返回压缩后的路径
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static List<String> saveBitmapFileList(List<String> path, int width, int height) {

        setDownloadPath();

        List<String> paths = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            Bitmap bitmap = decodeSampledBitmapFromPath(path.get(i), width, height);
            long timeMillis = System.currentTimeMillis();
            String save_path = Environment.getExternalStorageDirectory().getPath() + com.binvshe.binvshe.binvsheui.chen.Utils.Constants.ZANPLUSAPP_PATH_DOWNLOAD + timeMillis + ".jpg";
            paths.add(save_path);
            File file = new File(save_path);//将要保存图片的路径
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (bos != null) {
                    try {
                        bos.flush();
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return paths;
    }

    /**
     * 判断是否存在这个路径，不存在的话创建
     */
    public static void setDownloadPath() {
        String save_path_parent = Environment.getExternalStorageDirectory().getPath() + com.binvshe.binvshe.binvsheui.chen.Utils.Constants.ZANPLUSAPP_PATH_DOWNLOAD;
        File file = new File(save_path_parent);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 清空压缩图片的文件夹
     */
    public static void deleteFolderFile(String path, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(path)) {
            try {
                File file = new File(path);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    }
//                        else {// 目录
//                            if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
//                                file.delete();
//                            }
//                        }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
