package com.glg.jetpacktest;

import androidx.databinding.ObservableField;

/**
 * Description:
 *
 * @package: com.glg.jetpacktest
 * @className: User
 * @author: gao
 * @date: 2020/8/24 10:47
 */
public class User {

//    private String userName;
//
//    private String userGender;
//
//    public User(String userName, String userGender) {
//        this.userName = userName;
//        this.userGender = userGender;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserGender() {
//        return userGender;
//    }
//
//    public void setUserGender(String userGender) {
//        this.userGender = userGender;
//    }

    public final ObservableField<String> userName = new ObservableField<>();

    public final ObservableField<String> userGender = new ObservableField<>();
}
