package com.glg.headerintoplist1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by gao on 2020/7/28 17:07.
 * Function:
 */
class MyDecoration extends RecyclerView.ItemDecoration {

    private List<Group> groupList;
    private Paint paint;
    private Rect rect;
    private int titleHeight;
    private int titleBg = Color.parseColor("#FF03DAC5");
    private int fontColor = Color.parseColor("#FF000000");
    private int titleFontSize;

    public MyDecoration(Context context, List<Group> groupList) {
        this.groupList = groupList;
        paint = new Paint();
        rect = new Rect();
        titleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        titleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
        paint.setTextSize(titleFontSize);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (position > -1) {
                if (position == 0) {
                    drawTitleArea(c, left, right, child, params, position);
                } else {
                    if (null != groupList.get(position).getGroupName() && !groupList.get(position).getGroupName().equals(groupList.get(position - 1).getGroupName())) {
                        drawTitleArea(c, left, right, child, params, position);
                    } else {
                    }
                }
            }
        }
    }

    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {
        paint.setColor(titleBg);
        c.drawRect(left, child.getTop() - params.topMargin - titleHeight, right, child.getTop() - params.topMargin, paint);
        paint.setColor(fontColor);

        paint.getTextBounds(groupList.get(position).getGroupName(), 0, groupList.get(position).getGroupName().length(), rect);
        c.drawText(groupList.get(position).getGroupName(), child.getPaddingLeft(), child.getTop() - params.topMargin - (titleHeight / 2 - rect.height() / 2), paint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();

        String tag = groupList.get(pos).getGroupName();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;

        boolean flag = false;
        if ((pos + 1) < groupList.size()) {
            if (null != tag && !tag.equals(groupList.get(pos + 1).getGroupName())) {
                Log.d("zxt", "onDrawOver() called with: c = [" + child.getTop());
                if (child.getHeight() + child.getTop() < titleHeight) {
                    c.save();
                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - titleHeight);
                }
            }
        }
        paint.setColor(titleBg);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + titleHeight, paint);
        paint.setColor(fontColor);
        paint.getTextBounds(tag, 0, tag.length(), rect);
        c.drawText(tag, child.getPaddingLeft(),
                parent.getPaddingTop() + titleHeight - (titleHeight / 2 - rect.height() / 2),
                paint);
        if (flag)
            c.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (position == 0) {
                outRect.set(0, titleHeight, 0, 0);
            } else {
                if (null != groupList.get(position).getGroupName() && !groupList.get(position).getGroupName().equals(groupList.get(position - 1).getGroupName())) {
                    outRect.set(0, titleHeight, 0, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        }
    }
}
