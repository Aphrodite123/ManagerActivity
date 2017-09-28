package com.aphrodite.manageractivity.config;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.aphrodite.manageractivity.db.GreenDaoManager;
import com.aphrodite.manageractivity.util.Logger;

/**
 * Created by Aphrodite on 2017/9/16.
 */

public class ManagerApplication extends Application {
  private static final String TAG = ManagerApplication.class.getSimpleName();

  private static ManagerApplication mApplication = null;
  private static GreenDaoManager mDaoManager = null;

  @Override
  public void onCreate() {
    super.onCreate();
    initSystem();
  }

  private void initSystem() {
    this.mApplication = this;

    Logger.init(this);

    initGreenDao();
  }

  private void initGreenDao() {
    this.mDaoManager = GreenDaoManager.getInstance(this);
  }

  public static ManagerApplication getApp() {
    return mApplication;
  }

  public static GreenDaoManager getDaoManager() {
    return mDaoManager;
  }

  public static void setDaoManager(GreenDaoManager daoManager) {
    ManagerApplication.mDaoManager = daoManager;
  }

  /**
   * 获取当前Activity名称
   *
   * @return
   */
  public String getRunningActivityName() {
    ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    return runningActivity;
  }
}
