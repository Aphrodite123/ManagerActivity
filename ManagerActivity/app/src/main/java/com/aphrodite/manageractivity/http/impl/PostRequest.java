package com.aphrodite.manageractivity.http.impl;

import com.aphrodite.manageractivity.http.abs.HttpRequest;
import com.aphrodite.manageractivity.http.inter.OnResponseListener;
import com.aphrodite.manageractivity.util.TextUtils;

/**
 * Created by Aphrodite on 2017/11/19.
 */

public class PostRequest extends HttpRequest {
  public static final String PARA_FORMAT = "para";

  private OnResponseListener mResponseListener;

  public PostRequest(String path, String para, OnResponseListener responseListener) {
    super(path);
    this.mResponseListener = responseListener;
    String encodePara = PARA_FORMAT + HTTP_REQ_ENTITY_MERGE + para;
    this.mData = TextUtils.getBytesByUTF8(encodePara);
  }

  @Override
  public String getUrl() {
    return getBaseUrl();
  }

  @Override
  protected void onRequest(int code, String response) {
    mResponseListener.onResponse(code, response);
  }
}
