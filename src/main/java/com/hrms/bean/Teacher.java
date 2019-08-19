package com.hrms.bean;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
public class Teacher {
    private Integer teachId;
    private String teachTitle;
    private String teachName;
    private Integer teachPassword;

    public Integer getTeachPassword() {
        return teachPassword;
    }

    public void setTeachPassword(Integer teachPassword) {
        this.teachPassword = teachPassword;
    }

    public Integer getTeachId() {
        return teachId;
    }

    public void setTeachId(Integer teachId) {
        this.teachId = teachId;
    }

    public String getTeachTitle() {
        return teachTitle;
    }

    public void setTeachTitle(String teachTitle) {
        this.teachTitle = teachTitle;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public Teacher() {
    }

    public Teacher(Integer teachId, String teachTitle, String teachName) {
        this.teachId = teachId;
        this.teachTitle = teachTitle;
        this.teachName = teachName;
    }

    public Teacher(Integer teachId, String teachTitle, String teachName, Integer teachPassword) {
        this.teachId = teachId;
        this.teachTitle = teachTitle;
        this.teachName = teachName;
        this.teachPassword = teachPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teachId=" + teachId +
                ", teachTitle='" + teachTitle + '\'' +
                ", teachName='" + teachName + '\'' +
                '}';
    }
}
