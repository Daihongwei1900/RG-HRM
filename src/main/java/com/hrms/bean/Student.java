package com.hrms.bean;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
public class Student {
    private Integer stuId;
    private String stuName;
    private String stuEmail;
    private String stuGender;
    private Integer teacherId;

    private Teacher teacher;

    public Student(Integer stuId, String stuName, String stuEmail, String stuGender, Integer teacherId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.teacherId = teacherId;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", teacherId=" + teacherId +
                ", teacher=" + teacher +
                '}';
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}