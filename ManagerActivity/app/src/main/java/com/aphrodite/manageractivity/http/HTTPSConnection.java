package com.aphrodite.manageractivity.http;

import com.aphrodite.manageractivity.BuildConfig;
import com.aphrodite.manageractivity.util.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by Aphrodite on 2017/11/18.
 */

public class HTTPSConnection extends BaseConnection {
  private static final String TAG = HTTPSConnection.class.getSimpleName();
  private HttpsURLConnection mHttpsURLConnection = null;
  private SSLContext mSslContext = null;

  public HTTPSConnection(String url) {
    super();
    TrustManager tm = null;
    tm = MyX509TrustManager.getInstance();

    try {
      mSslContext = SSLContext.getInstance("TLS");
      mSslContext.init(null, new TrustManager[]{tm}, null);
      mHttpsURLConnection = (HttpsURLConnection) new URL(url).openConnection();
      mHttpsURLConnection.setDefaultSSLSocketFactory(mSslContext.getSocketFactory());
    } catch (NoSuchAlgorithmException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter HTTPSConnection method.NoSuchAlgorithmException: " + e);
      }
    } catch (KeyManagementException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter HTTPSConnection method.KeyManagementException: " + e);
      }
    } catch (MalformedURLException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter HTTPSConnection method.MalformedURLException: " + e);
      }
    } catch (IOException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter HTTPSConnection method.IOException: " + e);
      }
    }
  }

  @Override
  protected HttpURLConnection getURLConnection() {
    return mHttpsURLConnection;
  }
}
