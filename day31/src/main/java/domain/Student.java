package domain;

import java.io.Serializable;
import java.util.Date;

// domain object
// 1. 提供一个无参构造
// 2. 提供一个 toString (敏感信息不要放在 toString中)
// 3. 建议实现 Serializable 接口
public class Student implements Serializable {
    private Integer sid;
    private String sname;
    private Date birthday;
    private String sex;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                '}';
    }
}
