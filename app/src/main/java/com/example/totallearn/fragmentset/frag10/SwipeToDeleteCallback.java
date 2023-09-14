package com.example.totallearn.fragmentset.frag10;

import android.animation.ObjectAnimator;
import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.example.totallearn.R;

public abstract class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private Drawable deleteIcon;
    private ColorDrawable background;

    public SwipeToDeleteCallback(Context context) {
        super(0, ItemTouchHelper.LEFT);
        deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background); // 自定义删除图标
        background = new ColorDrawable(ContextCompat.getColor(context, R.color.blue)); // 自定义删除背景颜色
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        onSwipe(position);
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        View itemView = viewHolder.itemView;
//        View viewById = viewHolder.itemView.findViewById(R.id.tv_delete);

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // 计算删除按钮的宽度
            int deleteButtonWidth = itemView.findViewById(R.id.tv_delete).getWidth();
            // 计算 Item 的右边界
            int itemRight = itemView.getRight();

            // 如果 dX 大于等于删除按钮的宽度，说明删除按钮完全显示，将 dX 限制为删除按钮的宽度
            if (dX >= deleteButtonWidth) {
                dX = deleteButtonWidth;
            }

            // 设置删除按钮布局的位置
            itemView.layout(0, itemView.getTop(), itemRight + (int) dX, itemView.getBottom());

            // 获取删除按钮的布局
            View deleteButton = itemView.findViewById(R.id.tv_delete);

            // 设置删除按钮的位置，让它跟在 Item 后面
            deleteButton.layout(itemRight, itemView.getTop(), itemRight + deleteButtonWidth, itemView.getBottom());
        }
    }





    public abstract void onSwipe(int position);
}
