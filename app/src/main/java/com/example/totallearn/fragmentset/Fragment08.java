package com.example.totallearn.fragmentset;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.totallearn.R;
import com.example.totallearn.designmode.abstractfactorymode.FarmTest;
import com.example.totallearn.designmode.adaptermode.ClassAdapterTest;
import com.example.totallearn.designmode.bridgemode.BridgeTest;
import com.example.totallearn.designmode.prototypemode.PrototypeTest;

/**
 * Created by pateo on 18-12-27.
 */

public class Fragment08 extends Fragment implements View.OnClickListener {

    public static final String TAG = Fragment08.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_08,container,false);
        Button f8b1 = view.findViewById(R.id.f8_b1);
        Button f8b2 = view.findViewById(R.id.f8_b2);
        Button f8b3 = view.findViewById(R.id.f8_b3);


        Button f8b10 = view.findViewById(R.id.f8_b10);

        f8b1.setOnClickListener(this);
        f8b2.setOnClickListener(this);
        f8b3.setOnClickListener(this);

        f8b10.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.f8_b1:
                FarmTest ft = new FarmTest();
                break;
            case R.id.f8_b2:
                try {
                    PrototypeTest pt = new PrototypeTest();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.f8_b3:
                ClassAdapterTest ct = new ClassAdapterTest();
                break;

            case R.id.f8_b10:
                BridgeTest bridgeTest = new BridgeTest();
                break;

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


}
