package com.glg.jetpacktest;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Description:
 *
 * @package: com.glg.jetpacktest
 * @className: Dog
 * @author: gao
 * @date: 2020/8/24 11:33
 */
public class Dog extends BaseObservable {

    private String name;

    private String gender;

    public Dog(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
