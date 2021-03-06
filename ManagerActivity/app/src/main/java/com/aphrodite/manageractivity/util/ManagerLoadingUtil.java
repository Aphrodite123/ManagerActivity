package com.aphrodite.manageractivity.util;

import android.content.Context;

import com.aphrodite.manageractivity.R;
import com.aphrodite.manageractivity.ui.view.ManagerLoadingDialog;

/**
 * Created by Aphrodite on 2017/9/16.
 */

public class ManagerLoadingUtil {
  public static ManagerLoadingDialog dialog(Context context) {
    ManagerLoadingDialog dialog = new ManagerLoadingDialog(context, R.style.loading_dialog);
    dialog.setCancelable(true);
    return dialog;
  }

  public static ManagerLoadingDialog dialog(Context context, boolean cancelable) {
    ManagerLoadingDialog dialog = new ManagerLoadingDialog(context, R.style.loading_dialog);
    dialog.setCancelable(cancelable);
    return dialog;
  }

  public static ManagerLoadingDialog show(Context context, boolean cancelable) {
    ManagerLoadingDialog dialog = dialog(context, cancelable);
    dialog.show();
    return dialog;
  }
}
