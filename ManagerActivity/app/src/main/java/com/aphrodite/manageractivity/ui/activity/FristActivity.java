package com.aphrodite.manageractivity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aphrodite.manageractivity.R;
import com.aphrodite.manageractivity.base.BaseActivity;
import com.aphrodite.manageractivity.db.RealmManager;
import com.aphrodite.manageractivity.entity.Dog;
import com.aphrodite.manageractivity.http.HTTPServer;
import com.aphrodite.manageractivity.http.impl.GetRequest;
import com.aphrodite.manageractivity.http.inter.OnResponseListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FristActivity extends BaseActivity implements View.OnClickListener {
  private static final String TAG = FristActivity.class.getSimpleName();
  @BindView(R.id.main_info)
  TextView mTextView;
  @BindView(R.id.main_btn)
  TextView mButton;
  @BindView(R.id.main_get_request_btn)
  Button mGetRequestBtn;
  @BindView(R.id.main_post_request_btn)
  Button mPostRequestBtn;
  @BindView(R.id.main_add_btn)
  Button mAddBtn;
  @BindView(R.id.main_delete_btn)
  Button mDeleteBtn;
  @BindView(R.id.main_update_btn)
  Button mUpadteBtn;
  @BindView(R.id.main_query_btn)
  Button mQueryBtn;

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
    mGetRequestBtn.setOnClickListener(this);
    mPostRequestBtn.setOnClickListener(this);
    mAddBtn.setOnClickListener(this);
    mDeleteBtn.setOnClickListener(this);
    mUpadteBtn.setOnClickListener(this);
    mQueryBtn.setOnClickListener(this);
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
      case R.id.main_get_request_btn:
        sendGetRequest();
        break;
      case R.id.main_add_btn:
        Dog dog = new Dog();
        dog.setId("001");
        dog.setName("阿黄");
        dog.setAge(2);

        RealmManager.getInstance().insert(dog);

        break;
      case R.id.main_delete_btn:
        RealmManager.getInstance().delete("001");
        break;
      case R.id.main_update_btn:
        RealmManager.getInstance().update("001", "小黄");
        break;
      case R.id.main_query_btn:
        List<Dog> dogs = new ArrayList<>();
        dogs = (List<Dog>) RealmManager.getInstance().queryAllObject();
        if (null == dogs || dogs.size() < 1) {
          mTextView.setText("");
          return;
        }
        String info = "";
        for (Dog d : dogs) {
          info = info + d.toString() + "\n";
        }
        mTextView.setText(info);
        break;
    }
  }

  private void sendGetRequest() {
    OnResponseListener handle = new OnResponseListener() {
      @Override
      public void onResponse(int code, String response) {
        mTextView.setText(response);
      }
    };
    String path = "/sma-upload/getStudentInfo";
    GetRequest request = new GetRequest(path, "", handle);
    HTTPServer.getInstance().doRequest(request);
  }

//  private void sendPostRequest() {
//    String para = mRequestParaEditText.getText().toString();
//    if (null != para && para.length() > 0) {
//      TestRequestHandler handle = new TestRequestHandler();
//      PostRequest request = new PostRequest(para, handle);
//      HTTPServer.getInstance().doRequest(request);
//    } else {
//      showResult("请在输入框输入请求内容！");
//    }
//  }

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
