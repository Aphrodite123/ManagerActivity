package com.aphrodite.manageractivity.http;

import android.text.TextUtils;

import com.aphrodite.manageractivity.BuildConfig;
import com.aphrodite.manageractivity.http.abs.HttpRequest;
import com.aphrodite.manageractivity.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpURLConnection封装基类.设置请求协议头、发送请求
 * Created by Aphrodite on 2017/10/26.
 */

public abstract class BaseConnection {
  private static final String TAG = BaseConnection.class.getSimpleName();

  protected static final String HTTP_REQ_PROPERTY_CHARSET = "Accept-Charset";
  protected static final String HTTP_REQ_VALUE_CHARSET = "UTF-8";
  protected static final String HTTP_REQ_PROPERTY_CONTENT_TYPE = "Content-Type";
  protected static final String HTTP_REQ_VALUE_CONTENT_TYPE = "application/x-www-form-urlencoded";
  protected static final String HTTP_REQ_PROPERTY_CONTENT_LENGTH = "Content-Length";
  protected static final String HTTP_REQ_METHOD_GET = "GET";
  protected static final String HTTP_REQ_METHOD_POST = "POST";
  protected static final String HTTP_REQ_COOKIE = "Cookie";

  /**
   * 建立连接的超时时间
   */
  protected static final int CONNECT_TIMEOUT = 5 * 1000;
  /**
   * 建立资源连接后,从 input 流读入时的超时时间
   */
  protected static final int DEFAULT_READ_TIMEOUT = 10 * 1000;

  public BaseConnection() {
  }

  private void setCommonParams() {
    HttpURLConnection connection = getURLConnection();
    if (null == connection) {
      return;
    }
    connection.setConnectTimeout(CONNECT_TIMEOUT);
    connection.setReadTimeout(DEFAULT_READ_TIMEOUT);
    connection.setUseCaches(false);
    connection.setRequestProperty(HTTP_REQ_PROPERTY_CHARSET, HTTP_REQ_VALUE_CHARSET);
    connection.setRequestProperty(HTTP_REQ_PROPERTY_CONTENT_TYPE, HTTP_REQ_VALUE_CONTENT_TYPE);
  }

  private void setCommonCookies(HashMap<String, String> cookies) {
    if (null == cookies || cookies.size() < 1) {
      return;
    }

    HttpURLConnection connection = getURLConnection();
    if (null == connection) {
      return;
    }

    String cookieStr = connection.getRequestProperty(HTTP_REQ_COOKIE);
    if (!TextUtils.isEmpty(cookieStr)) {
      StringBuffer buffer = new StringBuffer();
      buffer.append(cookieStr).append(";");
      cookieStr = buffer.toString();
    } else {
      cookieStr = "";
    }

    String key = "";
    String value = "";
    for (Map.Entry<String, String> entry : cookies.entrySet()) {
      key = entry.getKey();
      value = entry.getValue();
      if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
        cookieStr = cookieStr + key + HttpRequest.HTTP_REQ_ENTITY_MERGE + value + ";";
      }
    }
    connection.setRequestProperty(HTTP_REQ_COOKIE, cookieStr);
  }

  public String doRequest(HttpRequest request) {
    if (null == getURLConnection()) {
      return "";
    }
    setCommonParams();
    //检查cookie
    if (null != request.mCookies && request.mCookies.size() > 0) {
      setCommonCookies(request.mCookies);
    }
    if (null == request.mData) {
      return doGetRequest();
    } else {
      return doPostRequest(request.mData);
    }
  }

  protected String doGetRequest() {
    String result = "";
    InputStream is = null;
    BufferedReader br = null;
    try {
      HttpURLConnection connection = getURLConnection();
      if (null == connection) {
        return "";
      }
      connection.setRequestMethod(HTTP_REQ_METHOD_GET);
      connection.connect();
      is = connection.getInputStream();
      br = new BufferedReader(new InputStreamReader(is, HTTP_REQ_VALUE_CHARSET));
      String line = null;
      StringBuffer sb = new StringBuffer();
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      result = sb.toString();
    } catch (javax.net.ssl.SSLHandshakeException e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter doGetRequest method.SSLHandshakeException: " + e);
      }
    } catch (Exception e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter doGetRequest method.Exception: " + e);
      }
    } finally {
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter doGetRequest method.IOException for close br: " + e);
        }
      }
      try {
        if (is != null) {
          is.close();
        }
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter doGetRequest method.IOException for close is: " + e);
        }
      }
      return result;
    }
  }

  protected String doPostRequest(byte[] data) {
    BufferedReader br = null;
    InputStream inptStream = null;
    OutputStream outputStream = null;
    try {
      HttpURLConnection connection = getURLConnection();
      if (null == connection) {
        return "";
      }
      connection.setRequestMethod(HTTP_REQ_METHOD_POST);
      connection.setRequestProperty(HTTP_REQ_PROPERTY_CONTENT_LENGTH, String.valueOf(data.length));
      //获得输出流，向服务器写入数据
      outputStream = connection.getOutputStream();
      outputStream.write(data);

      int response = connection.getResponseCode();//获得服务器的响应码
      if (response == HttpURLConnection.HTTP_OK) {
        inptStream = connection.getInputStream();
        br = new BufferedReader(new InputStreamReader(inptStream, HTTP_REQ_VALUE_CHARSET));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
          sb.append(line);
        }
        return sb.toString();
      }
    } catch (Exception e) {
      if (BuildConfig.DEBUG_SWITCH) {
        Logger.d(TAG, "Enter doPostRequest method.Exception: " + e);
      }
    } finally {
      try {
        if (outputStream != null) {
          outputStream.close();
        }
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter doPostRequest method.IOException for close outputStream: " + e);
        }
      }
      try {
        if (br != null) {
          br.close();
        }
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter doPostRequest method.IOException for close br: " + e);
        }
      }
      try {
        if (inptStream != null) {
          inptStream.close();
        }
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter doPostRequest method.IOException for close inptStream: " + e);
        }
      }
    }
    return "";
  }

  public int getResponseCode() {
    HttpURLConnection connection = getURLConnection();
    if (null == connection) {
      return -1;
    } else {
      try {
        return connection.getResponseCode();
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter getResponseCode method.IOException: " + e);
        }
        return -1;
      }
    }
  }

  public String getResponseMessage() {
    HttpURLConnection connection = getURLConnection();
    if (null == connection) {
      return "";
    } else {
      try {
        return connection.getResponseMessage();
      } catch (IOException e) {
        if (BuildConfig.DEBUG_SWITCH) {
          Logger.d(TAG, "Enter getResponseMessage method.IOException: " + e);
        }
        return "";
      }
    }
  }

  protected abstract HttpURLConnection getURLConnection();

}
