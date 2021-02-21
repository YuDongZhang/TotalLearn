package com.example.totallearn.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.totallearn.R;

public class DialogUtil {
    public static void ProgressBarCircleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.dialog_progressbar_circle, null);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText("请等待...");
        builder.setView(view);
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        AlertDialog mProgress = builder.create();
//        mProgress.setCanceledOnTouchOutside(false);
//        mProgress.setCancelable(false);
        mProgress.show();
    }
}
