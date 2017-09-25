package com.aphrodite.manageractivity.db.upgrade.abs;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Aphrodite on 2017/9/20.
 */

public abstract class AbstractMigratorHelper {
  public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
