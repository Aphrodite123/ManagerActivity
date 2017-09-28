package com.aphrodite.manageractivity.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.aphrodite.manageractivity.util.Logger;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DownloadFileInfoDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Aphrodite on 2017/9/20.
 */

public class DBMigrationHelper extends DaoMaster.OpenHelper {
  private static final String TAG = DBMigrationHelper.class.getSimpleName();

  public DBMigrationHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
    super(context, name, factory);
  }

  @Override
  public void onUpgrade(Database db, int oldVersion, int newVersion) {
    Logger.d(TAG, "Enter onUpgrade method.oldVersion: " + oldVersion + " newVersion: " + newVersion);
    MigrationHelper.getInstance().migrate(db, DownloadFileInfoDao.class);
  }

}
