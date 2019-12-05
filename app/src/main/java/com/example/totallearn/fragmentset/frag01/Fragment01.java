package com.example.totallearn.fragmentset.frag01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.totallearn.PdfActivity;
import com.example.totallearn.R;
import com.example.totallearn.base.BaseFragment;
import com.example.totallearn.serviceset.TestService;
import com.example.totallearn.utils.MyLogUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment01 extends BaseFragment {

    public static final String TAG = Fragment01.class.getSimpleName();
    @BindView(R.id.f1_tv2)
    TextView f1Tv2;
    Unbinder unbinder;
    private Button f01bt1;
    private Button f01bt3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");

        /**
         * 测试 看看
         */
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @OnClick({R.id.f1_bt1, R.id.f1_bt2, R.id.f1_bt5, R.id.f1_bt3, R.id.f1_bt4, R.id.f1_tv2, R.id.f1_tv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.f1_bt1:
                //在service 中启动服务要 getActivity
                Intent intent = new Intent(getActivity(), TestService.class);
                getActivity().startService(intent);
                break;
            case R.id.f1_bt2:
                //在service 中启动服务要 getActivity
                intent = new Intent(getActivity(), TestService.class);
                getActivity().stopService(intent);
                break;
            case R.id.f1_bt3:
                intent = new Intent(getActivity(), PdfActivity.class);
                startActivity(intent);
                break;
            case R.id.f1_bt4:
                test4();
                break;
            case R.id.f1_bt5:

                break;
            case R.id.f1_tv2:

                break;
            case R.id.f1_tv3:
                intent = new Intent(getActivity(),ImgCompressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void test4() {
        MyLogUtil.i(TAG, "onclick");
        //<img src"manager/upload/5920032-2.gif\>"\u003e前伸\u003cimg src\u003d\"manager/upload/02003.gif\"\u003e时有早接触
        String s = "乳腺恶性淋巴瘤的声像图特征是：<img src\"manager/upload/5920032-2.gif\\>①肿块常单发，呈圆球状或分叶状②肿块常多发，形态不规则③肿块较大，常>10cm，少数<5cm④肿块较小，常<3cm⑤肿块边界清晰，有包膜样回声⑥肿块边界不清晰，无包膜样回声⑦内呈低回声均匀，后方声加强⑧内呈高回声，不均匀，后方声衰减";
        s = TextUtils.htmlEncode(s);
        //  MyLogUtil.d(TAG, TextUtils.htmlEncode(s));
        //   MyLogUtil.d(TAG, s);
        //  f1Tv2.setText(getClickableHtml(s));
        test();
    }

    public void test() {
        // 按指定模式在字符串查找
        String line = "少数<5cm④肿块较小，常<3cm⑤肿块边界清晰";
        String pattern = "<\\d*";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find()) {
            MyLogUtil.d(TAG, m.group());
            line = line.replace(m.group(), "XX" + m.group().charAt(1));
        }
        MyLogUtil.d(TAG, line);
    }

    private CharSequence getClickableHtml(String html) {
        // Spanned spannedHtml = Html.fromHtml(html, imageGetter, null);
        Spanned spannedHtml = Html.fromHtml(html, null, null);
        MyLogUtil.d(TAG, "  xxx  " + html);
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);

        // ImageSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), ImageSpan.class);
        MyLogUtil.v(TAG, "spannedHtml " + spannedHtml.length());
       /* for (final ImageSpan span : urls) {
            MyLogUtil.e(TAG,"span  "+span.getSource());
            if (span.getSource().equals("type")) {//自己加的图片标签，不用添加点击事件

                continue;
            } else {
               // setLinkClickable(clickableHtmlBuilder, span);
            }
        }*/
        return clickableHtmlBuilder;
    }


}
