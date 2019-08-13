package com.hrms.service;

import com.hrms.bean.Student;
import com.hrms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public int getStuCount(){
        return studentMapper.countStus();
    }
    public List<Student> getStuList(Integer offser, Integer limit){
        return studentMapper.selectByLimitAndOffset(offser, limit);
    }
    public Student getStuById(Integer stuId){
        return studentMapper.selectOneById(stuId);
    }
    public Student getStuByName(String stuName){return studentMapper.selectOneByName(stuName);};
    public int updateStuById(Integer stuId, Student student){return studentMapper.updateOneById(stuId, student);}
    public int deleteStuById(Integer stuId){
        return studentMapper.deleteOneById(stuId);
    }
    public int addStu(Student student){
        return studentMapper.insertOne(student);
    }


}
