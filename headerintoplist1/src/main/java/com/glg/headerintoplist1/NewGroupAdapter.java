package com.glg.headerintoplist1;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Description:
 *
 * @package: com.glg.headerintoplist1
 * @className: NewGroupAdapter
 * @author: gao
 * @date: 2020/9/17 11:21
 */
public class NewGroupAdapter extends BaseSectionQuickAdapter<GroupSection, BaseViewHolder> {

    public NewGroupAdapter(int sectionHeadResId, int layoutResId, @Nullable List<GroupSection> data) {
        super(sectionHeadResId, layoutResId, data);
    }

    @Override
    protected void convertHeader(@NotNull BaseViewHolder baseViewHolder, @NotNull GroupSection groupSection) {
        baseViewHolder.setText(R.id.group, groupSection.getGroupName());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GroupSection groupSection) {

    }
}
