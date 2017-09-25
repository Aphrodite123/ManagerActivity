package com.aphrodite.manageractivity.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.aphrodite.manageractivity.R;

/**
 * Created by Aphrodite on 2017/9/16.
 */

public class ManagerLoadingDialog extends Dialog {
  private ImageView mImageView;
  private AnimationDrawable mAnimation;
  private Context mContext;

  public ManagerLoadingDialog(Context context, int theme) {
    super(context, theme);
    mContext = context;
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mImageView = new ImageView(mContext);
    mImageView.setImageResource(R.drawable.loading_dialog);
    setContentView(mImageView);
    mAnimation = (AnimationDrawable) mImageView.getDrawable();
    mAnimation.start();
  }
}
