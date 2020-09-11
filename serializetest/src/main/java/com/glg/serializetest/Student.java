package com.glg.serializetest;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * Description:
 *
 * @package: com.glg.serializetest
 * @className: Student
 * @author: gao
 * @date: 2020/9/1 11:02
 */
public class Student implements Serializable {

    //private static final long serialVersionUID = -6060343040263809614L;

    private String userName;
    private transient String password;//transient关键字可以在序列化时影藏该属性的值
    private String years;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Student(String userName, String password, String years) {
        this.userName = userName;
        this.password = password;
        this.years = years;
    }

    public Student() {
    }

    public void writeObject(ObjectOutput out) throws IOException {
        out.writeObject(userName + "===");
        out.writeObject(years + "===");
    }

    public void readObject(ObjectInput in) throws ClassNotFoundException, IOException {
        userName = (String)in.readObject();
        years = (String)in.readObject();
    }
}
