package com.hrms.bean;

import lombok.Data;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
@Data
public class Student {
    private Integer stuId;
    private String stuName;
    private String stuEmail;
    private String stuGender;
    private Integer teacherId;
    private Integer stuPassword;

    private Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", teacherId=" + teacherId +
                ", stuPassword=" + stuPassword +
                '}';
    }

    public Student() {
    }

    public Student(Integer stuId, String stuName, String stuEmail, String stuGender, Integer teacherId) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.teacherId = teacherId;
    }

    public Student(Integer stuId, String stuName, String stuEmail, String stuGender, Integer teacherId, Integer stuPassword) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuEmail = stuEmail;
        this.stuGender = stuGender;
        this.teacherId = teacherId;
        this.stuPassword = stuPassword;
    }

}
