package com.aphrodite.manageractivity.db.upgrade.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.aphrodite.manageractivity.util.Logger;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DownloadFileInfoDao;

/**
 * Created by Aphrodite on 2017/9/20.
 */

public class DBMigrationHelper extends DaoMaster.OpenHelper {
  private static final String TAG = DBMigrationHelper.class.getSimpleName();

  public DBMigrationHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
    super(context, name, factory);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Logger.d(TAG, "Enter onUpgrade method.oldVersion: " + oldVersion + " newVersion: " + newVersion);
    if (oldVersion < 2 && newVersion >= 2) {
      updateDbForTwo(db);
    }
  }

  private void updateDbForTwo(SQLiteDatabase db) {
    Logger.d(TAG, "Enter updateDbForTwo method.");
    String addName = "ALTER TABLE " + DownloadFileInfoDao.TABLENAME + " ADD COLUMN name VARCHAR";
    db.execSQL(addName);
  }
}
