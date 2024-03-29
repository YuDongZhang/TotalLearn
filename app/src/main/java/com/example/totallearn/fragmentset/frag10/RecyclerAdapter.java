package com.example.totallearn.fragmentset.frag10;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.totallearn.R;
import com.example.totallearn.fragmentset.frag10.ColorUtils;
import com.example.totallearn.fragmentset.frag10.ItemTouchStatus;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> implements ItemTouchStatus {

    private List<String> mDataList;

    public RecyclerAdapter(List<String> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inventroy, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.mTextView.setText(mDataList.get(i));
        recyclerViewHolder.mTextView.setBackgroundColor(Color.parseColor(ColorUtils.generateRandomColor()));
        recyclerViewHolder.mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.remove(recyclerViewHolder.getAdapterPosition());
                notifyItemRemoved(recyclerViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mDeleteView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            mDeleteView = itemView.findViewById(R.id.delete);
        }
    }
}