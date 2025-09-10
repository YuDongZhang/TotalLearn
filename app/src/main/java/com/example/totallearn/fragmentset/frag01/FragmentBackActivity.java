package com.example.totallearn.fragmentset.frag01;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.totallearn.R;
import com.example.totallearn.activity.base.BaseActivity;
import com.example.totallearn.databinding.ActivityFragmentBackBinding;
import com.example.totallearn.fragmentset.frag05.Fragment05;
import com.example.totallearn.fragmentset.frag06.Fragment06;

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

    private ActivityFragmentBackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.afT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment05 f5 = new Fragment05();
                FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
                // tf.add(R.id.frame_frag,f5);
                tf.replace(R.id.frame_frag,f5);
                tf.addToBackStack(null);
                tf.commit();
            }
        });

        binding.afT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment06 f6 = new Fragment06();
                FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
                tf.replace(R.id.frame_frag,f6);
                tf.addToBackStack(null);
                tf.commit();
            }
        });
    }
}