package com.aphrodite.manageractivity.http;

import com.aphrodite.manageractivity.BuildConfig;
import com.aphrodite.manageractivity.util.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aphrodite on 2017/11/18.
 */

public class HTTPConnection extends BaseConnection {
  private static final String TAG = HTTPConnection.class.getSimpleName();
  private HttpURLConnection mHttpURLConnection = null;

  public HTTPConnection(String url) {
    try {
      mHttpURLConnection = (HttpURLConnection) new URL(url).openConnection();
    } catch (IOException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter HTTPConnection method.IOException: " + e);
      }
    }
  }

  @Override
  protected HttpURLConnection getURLConnection() {
    return mHttpURLConnection;
  }
}
