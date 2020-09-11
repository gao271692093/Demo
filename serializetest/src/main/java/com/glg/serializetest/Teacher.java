package com.glg.serializetest;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Description:
 *
 * @package: com.glg.serializetest
 * @className: Teacher
 * @author: gao
 * @date: 2020/9/1 11:54
 */
public class Teacher implements Externalizable {

    private String name;
    private String sex;
    private int years;

    public Teacher() {
    }

    public Teacher(String name, String sex, int years) {
        this.name = name;
        this.sex = sex;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(sex);
        out.write(years);
    }

    @Override
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        name = (String)in.readObject();
        sex = (String)in.readObject();
        years = in.read();
    }
}
