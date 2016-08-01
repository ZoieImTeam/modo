package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.location.FileManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjy on 2016/1/19.
 * QQ 735030437
 * phone 15705926886
 */
public class ChenUtils {

    /**
     * 判断是否存在这个路径，不存在的话创建
     */
    public static void isHasAppPath() {
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + "附加需要的APP路径";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 用于拍照时获取输出的Uri
     *
     * @return
     * @version 1.0
     * @author zyh
     */
    public static Uri getOutputMediaFileUri() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(FileManager.getImagePath(BaseApp.mContext) + File.separator + "IMG_" + timeStamp + ".jpg");
//        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
    }

    /**
     * 把相册或相机拍下照片的本地路径传入，即可压缩并且返回压缩后的路径
     * 单张
     *
     * @param path   本地路径
     * @param width  压缩后所需要的宽
     * @param height 压缩后需要的高
     * @return
     */
    public static String saveBitmapFile(String path, int width, int height) {

        isHasAppPath();

        Bitmap bitmap = decodeSampledBitmapFromPath(path, width, height);

        String save_path = Environment.getExternalStorageDirectory().getPath() + Constants.ZANPLUSAPP_PATH_DOWNLOAD + "abc.jpg";
        File file = new File(save_path);//将要保存图片的路径
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            return save_path;
        } catch (IOException e) {
            e.printStackTrace();
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
        return path;
    }

    /**
     * 把相册或相机拍下照片的本地路径传入，即可压缩并且返回压缩后的路径
     * 多张
     *
     * @param path   本地路径
     * @param width  压缩后所需要的宽
     * @param height 压缩后需要的高
     * @return
     */
    public static List<String> saveBitmapFileList(List<String> path, int width, int height) {
        // 判断是否存在这个路径
        isHasAppPath();

        List<String> paths = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            Bitmap bitmap = decodeSampledBitmapFromPath(path.get(i), width, height);
            String save_path = Environment.getExternalStorageDirectory().getPath() + Constants.ZANPLUSAPP_PATH_DOWNLOAD + "abc" + i + ".jpg";
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
     * 验证合法字符
     *
     * @return
     */
    public static boolean checkCharacter(String character) {
        boolean flag = false;
        try {
            String check = "[a-zA-Z0-9_]{4,16}";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(character);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag = false;
        try {
            Pattern regex = Pattern
                    .compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean isIdcard(String idcard) {
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        if (idcard.length() != 18) {
            return false;
        }

        String substring = idcard.substring(0, 17);
        if (!isNumeric(substring)) {
            return false;
        }
        String subend = idcard.substring(17, 18);
        if (!Arrays.asList(ValCodeArr).contains(subend)) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符是否为中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为中文
     *
     * @param strName
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static int getMAX(int[] numbers) {
        int max = 0;
        for (int i = 0; i < numbers.length; i++) {
            max = max > numbers[i] ? max : numbers[i];
        }
        return max;
    }

    /**
     * 把本地图片转化为Bitmap类型
     * @param resId
     * @return
     */
    public static Bitmap samllImage(int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = BaseApp.mContext.getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
        return BitmapFactory.decodeStream(is,null,opt);
    }

}
