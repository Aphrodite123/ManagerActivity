package com.aphrodite.manageractivity.http.abs;

import com.aphrodite.manageractivity.BuildConfig;
import com.aphrodite.manageractivity.http.inter.OnResponseListener;
import com.aphrodite.manageractivity.util.Logger;

import java.util.HashMap;

/**
 * Created by Aphrodite on 2017/11/10.
 */

public abstract class HttpRequest {
  private static final String TAG = HttpRequest.class.getSimpleName();

  public static final String HTTP_REQ_ENTITY_MERGE = "=";
  public static final String HTTP_REQ_ENTITY_JOIN = "&";
  public static final String HTTP_REQ_QUESTION_MARK = "?";

  protected String mPath = "";
  public byte[] mData = null;
  public HashMap<String, String> mCookies = new HashMap<>();

  public HttpRequest(String path) {
    this.mPath = path;
  }

  public abstract String getUrl();

  protected abstract void onRequest(int code, String response);

  protected String getBaseUrl() {
    String url = BuildConfig.HOST_URL + mPath;
    if (BuildConfig.DEBUG_SWITCH) {
      Logger.d(TAG, "Enter getBaseUrl method.url: " + url);
    }
    return url;
  }

  public OnResponseListener mResponseListener = new OnResponseListener() {
    @Override
    public void onResponse(int code, String response) {
      onRequest(code, response);
    }
  };
}
