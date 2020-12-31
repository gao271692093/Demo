package com.glg.headerintoplist1;

import com.chad.library.adapter.base.entity.JSectionEntity;

/**
 * Description:
 *
 * @package: com.glg.headerintoplist1
 * @className: GroupSectionEntity
 * @author: gao
 * @date: 2020/9/17 11:23
 */
public class GroupSection extends JSectionEntity {

    private String groupName;

    @Override
    public boolean isHeader() {
        return false;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
