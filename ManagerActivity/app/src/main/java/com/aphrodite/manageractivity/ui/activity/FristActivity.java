package com.aphrodite.manageractivity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aphrodite.manageractivity.R;
import com.aphrodite.manageractivity.base.BaseActivity;

public class FristActivity extends BaseActivity implements View.OnClickListener {
  private static final String TAG = FristActivity.class.getSimpleName();
  private TextView mTextView;
  private TextView mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Enter onCreate method.");
    setContentView(R.layout.activity_main);
    initView();
    initData();
  }

  private void initView() {
    mTextView = findViewById(R.id.main_info);
    mButton = findViewById(R.id.main_btn);

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
