package com.aphrodite.manageractivity.http.impl;

import android.text.TextUtils;

import com.aphrodite.manageractivity.http.abs.HttpRequest;
import com.aphrodite.manageractivity.http.inter.OnResponseListener;

/**
 * Created by Aphrodite on 2017/11/19.
 */

public class GetRequest extends HttpRequest {
  public static final String PARA_FORMAT = "para";
  private String mPara = "";

  private OnResponseListener mResponseListener;

  public GetRequest(String path, String para, OnResponseListener responseListener) {
    super(path);
    this.mPara = para;
    this.mResponseListener = responseListener;
  }

  @Override
  public String getUrl() {
    StringBuffer buffer = new StringBuffer();
    String url = getBaseUrl();
    if (!TextUtils.isEmpty(mPara)) {
      buffer.append(PARA_FORMAT + HTTP_REQ_ENTITY_MERGE + mPara);
      url = url + HTTP_REQ_QUESTION_MARK + buffer.toString();
    }
    return url;
  }

  @Override
  protected void onRequest(int code, String response) {
    mResponseListener.onResponse(code, response);
  }
}
