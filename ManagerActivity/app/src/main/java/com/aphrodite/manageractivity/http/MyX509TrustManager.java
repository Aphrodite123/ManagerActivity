package com.aphrodite.manageractivity.http;

import android.util.Log;

import com.aphrodite.manageractivity.BuildConfig;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Aphrodite on 2017/11/18.
 */

public class MyX509TrustManager implements X509TrustManager {
  private static final String TAG = MyX509TrustManager.class.getSimpleName();
  private X509TrustManager mX509TrustManager;
  /*volatile:
    1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的.
    2）禁止进行指令重排序。
   */
  private static volatile MyX509TrustManager myX509TrustManager = null;

  public static MyX509TrustManager getInstance() {
    if (null == myX509TrustManager) {
      synchronized (MyX509TrustManager.class) {
        if (null == myX509TrustManager) {
          try {
            myX509TrustManager = new MyX509TrustManager();
          } catch (Exception e) {
            if (BuildConfig.DEBUG_SWITCH) {
              Log.d(TAG, "Enter getInstance method.Exception: " + e);
            }
          }
        }
      }
    }
    return myX509TrustManager;
  }

  public MyX509TrustManager() throws Exception {
    // create a "default" JSSE X509TrustManager.
    KeyStore ks = null;
    try {
      ks = KeyStore.getInstance("JKS");
    } catch (Exception e) {
      e.printStackTrace();
    }
    TrustManager tms[] = {};
    if (ks != null) {
      FileInputStream fis = null;
      try {
        fis = new FileInputStream("trustedCerts");
        ks.load(fis, "passphrase".toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        tmf.init(ks);
        tms = tmf.getTrustManagers();
      } finally {
        if (fis != null) {
          fis.close();
        }
        fis = null;
      }
    } else {
      TrustManagerFactory tmf = TrustManagerFactory
        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init((KeyStore) null);
      tms = tmf.getTrustManagers();
    }
    for (int i = 0; i < tms.length; i++) {
      if (tms[i] instanceof X509TrustManager) {
        mX509TrustManager = (X509TrustManager) tms[i];
        return;
      }
    }
    throw new Exception("Couldn't initialize");
  }

  @Override
  public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
    mX509TrustManager.checkClientTrusted(x509Certificates, s);
  }

  @Override
  public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
    mX509TrustManager.checkServerTrusted(x509Certificates, s);
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return mX509TrustManager.getAcceptedIssuers();
  }
}
