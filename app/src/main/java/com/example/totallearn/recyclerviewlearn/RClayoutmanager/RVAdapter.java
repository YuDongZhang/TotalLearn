package com.example.totallearn.recyclerviewlearn.RClayoutmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.totallearn.R;

import java.util.List;

/**
 * Created by Shinelon on 2019/4/21.
 */
//// ① 创建Adapter
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH>{
    private List<String> mDatas;

    public RVAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclayoutmanage_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.title.setText(mDatas.get(position));
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //③ 在Adapter中实现3个方法
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        public  TextView title;
        public VH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rc_manage_tv);
        }
    }
}
