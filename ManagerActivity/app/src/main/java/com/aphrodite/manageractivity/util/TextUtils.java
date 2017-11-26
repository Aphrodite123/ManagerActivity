package com.aphrodite.manageractivity.util;

import com.aphrodite.manageractivity.BuildConfig;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aphrodite on 2017/11/19.
 */

public class TextUtils {
  private static final String TAG = TextUtils.class.getSimpleName();

  public static byte[] getBytesByUTF8(String str) {
    try {
      return str.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter getBytesByUTF8 method.UnsupportedEncodingException: " + e);
      }
      return null;
    }
  }
}
