package com.glg.headerintoplist;

import java.util.List;

/**
 * Created by gao on 2020/7/27 16:32.
 * Function:
 */
class Group {

    private String groupName;
    private List<Item> groupContent;

    public Group(String groupName, List<Item> groupContent) {
        this.groupName = groupName;
        this.groupContent = groupContent;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Item> getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(List<Item> groupContent) {
        this.groupContent = groupContent;
    }
}
