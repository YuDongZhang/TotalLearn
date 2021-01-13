package com.example.totallearn;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class SCRAdapter extends RecyclerView.Adapter<SCRAdapter.VH> {

    private List<String> stringList;

    public SCRAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_swipe_scroll_recycler_item,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        public TextView textView;
        public VH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.swipe_scroll_tv);
        }
    }
}
