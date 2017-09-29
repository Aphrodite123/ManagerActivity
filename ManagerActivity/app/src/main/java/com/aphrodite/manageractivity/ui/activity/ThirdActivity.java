package com.aphrodite.manageractivity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aphrodite.manageractivity.R;
import com.aphrodite.manageractivity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Aphrodite on 2017/9/16.
 */

public class ThirdActivity extends BaseActivity implements View.OnClickListener {
  private static final String TAG = ThirdActivity.class.getSimpleName();
  @BindView(R.id.main_info)
  TextView mTextView;
  @BindView(R.id.main_btn)
  TextView mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
    initData();
  }

  @Override
  protected int getViewId() {
    return R.layout.activity_main;
  }

  private void initView() {
    mTextView.setText(TAG);
    mButton.setOnClickListener(this);
  }

  private void initData() {
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.main_btn:
        Intent intent = new Intent(ThirdActivity.this, FristActivity.class);
        startActivity(intent);
        break;
    }
  }
}
