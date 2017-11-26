package com.aphrodite.manageractivity.http;

import android.text.TextUtils;

import com.aphrodite.manageractivity.http.abs.HttpRequest;

import java.net.HttpURLConnection;

/**
 * Created by Aphrodite on 2017/11/18.
 */

public class HTTPServer {
  private static final String TAG = HTTPServer.class.getSimpleName();
  private static final String DOMAIN_PREFIXES = "https:";
  private static volatile HTTPServer mHttpServer = null;

  public static HTTPServer getInstance() {
    if (null == mHttpServer) {
      synchronized (HTTPServer.class) {
        if (null == mHttpServer) {
          mHttpServer = new HTTPServer();
        }
      }
    }
    return mHttpServer;
  }

  public void doRequest(HttpRequest request) {
    String url = request.getUrl();
    BaseConnection connection = null;
    if (TextUtils.isEmpty(url)) {
      return;
    }
    if (url.startsWith(DOMAIN_PREFIXES)) {
      connection = new HTTPSConnection(url);
    } else {
      connection = new HTTPConnection(url);
    }
    String result = connection.doRequest(request);
    int status = connection.getResponseCode();
    if (status == HttpURLConnection.HTTP_OK) {
      request.mResponseListener.onResponse(status, result);
    } else {
      if (TextUtils.isEmpty(result)) {
        String message = connection.getResponseMessage();
        request.mResponseListener.onResponse(status, message);
      } else {
        request.mResponseListener.onResponse(status, result);
      }
    }
  }

}
