package com.example.totallearn.fragmentset.frag01;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.totallearn.R;
import com.example.totallearn.base.BaseActivity;
import com.example.totallearn.fragmentset.frag05.Fragment05;
import com.example.totallearn.fragmentset.frag06.Fragment06;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    这个的作用就是添加 fragment
     Fragment05 f5 = new Fragment05();
    FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
    // tf.add(R.id.frame_frag,f5);
    tf.replace(R.id.frame_frag,f5);
    tf.addToBackStack(null);
    tf.commit();


    //在fragment 6界面做这个操作  确实达到了回退的效果
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
     fragmentManager.popBackStack();

 */

public class FragmentBackActivity extends BaseActivity {

    @BindView(R.id.frame_frag)
    FrameLayout frameFrag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_back);
        ButterKnife.bind(this);


        //fragmentTransaction.add(R.id)

    }


    @OnClick({R.id.af_t1, R.id.af_t2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.af_t1:
                Fragment05 f5 = new Fragment05();
                FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
                // tf.add(R.id.frame_frag,f5);
                tf.replace(R.id.frame_frag,f5);
                tf.addToBackStack(null);
                tf.commit();
                break;
            case R.id.af_t2:
                Fragment06 f6 = new Fragment06();
                 tf = getSupportFragmentManager().beginTransaction();
                tf.replace(R.id.frame_frag,f6);
                tf.addToBackStack(null);
                tf.commit();
                break;
        }
    }
}
