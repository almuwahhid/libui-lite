package com.almuwahhid.mylitelibs.app;

import android.graphics.Bitmap;
import com.almuwahhid.mylitelibs.utils.Base64Util;

import java.io.ByteArrayOutputStream;

public class LibUi {
    public static String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bao);
        byte[] byteArray = bao.toByteArray();
        return Base64Util.encodeBytes(byteArray);
    }
}
