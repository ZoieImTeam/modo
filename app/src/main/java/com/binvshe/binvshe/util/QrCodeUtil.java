package com.binvshe.binvshe.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by Administrator on 2016/3/24.
 */
public class QrCodeUtil {

    public static Bitmap createQRImage(int qrCodeWidth, int qrCodeHeight, String url) {

        Bitmap bitmap = null;

        try {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, qrCodeWidth, qrCodeHeight, hints);
            int[] pixels = new int[qrCodeWidth * qrCodeHeight];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < qrCodeHeight; y++) {
                for (int x = 0; x < qrCodeWidth; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * qrCodeWidth + x] = 0xff000000;
                    } else {
                        pixels[y * qrCodeWidth + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(qrCodeWidth, qrCodeHeight, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, qrCodeWidth, 0, 0, qrCodeWidth, qrCodeHeight);
            //显示到一个ImageView上面
        } catch (WriterException e) {
            e.printStackTrace();
        }finally {
            return bitmap;
        }
    }

}
