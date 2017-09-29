package com.aphrodite.manageractivity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aphrodite.manageractivity.R;
import com.aphrodite.manageractivity.base.BaseActivity;

import butterknife.BindView;

public class FristActivity extends BaseActivity implements View.OnClickListener {
  private static final String TAG = FristActivity.class.getSimpleName();
  @BindView(R.id.main_info)
  TextView mTextView;
  @BindView(R.id.main_btn)
  TextView mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Enter onCreate method.");
    initView();
    initData();
    /**
     * 插入数据库测试
     */
//    DownloadFileInfo fileInfo = new DownloadFileInfo(1, "英雄联盟.txt", "sdcard/", "www.baidu.com", 10000, 1024);
//    DownloadFileInfoDao fileInfoDao = ManagerApplication.getDaoManager().getDaoSession().getDownloadFileInfoDao();
//    fileInfoDao.insert(fileInfo);
  }

  @Override
  protected int getViewId() {
    return R.layout.activity_main;
  }

  private void initView() {
    mTextView.setText(this.toString() + "\n" + "current task id: " + this.getTaskId());
    mButton.setOnClickListener(this);
  }

  private void initData() {
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.main_btn:
        Intent intent = new Intent(FristActivity.this, SecondActivity.class);
        startActivity(intent);
//        showLoadingDialog();
        break;
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "Enter onResume method.");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "Enter onPause method.");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(TAG, "Enter onRestart method.");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "Enter onDestroy method.");
  }
}
