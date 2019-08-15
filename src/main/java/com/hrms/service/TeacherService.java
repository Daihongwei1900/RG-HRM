package com.hrms.service;

import com.hrms.bean.Teacher;
import com.hrms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    public int deleteTeachById(Integer teachId){
        return teacherMapper.deleteTeachById(teachId);
    }
    public int updateTeachById(Integer teachId, Teacher teacher){
        return teacherMapper.updateTeachById(teachId, teacher);
    }
    public int addTeach(Teacher teacher){
        return teacherMapper.insertTeach(teacher);
    }
    public int getTeachCount(){
        return teacherMapper.countTeachs();
    }
    public List<Teacher> getTeachList(Integer offset, Integer limit){
        return teacherMapper.selectTeachsByLimitAndOffset(offset, limit);
    };
    public Teacher getTeachById(Integer teachId){
        return teacherMapper.selectOneById(teachId);
    }

    public Teacher getTeachByName(String teachName){
        return teacherMapper.selectOneByName(teachName);
    }


    public List<Teacher> getTeachName(){
        return teacherMapper.selectTeachList();
    }

    public int updateTeachPassword(String username,Integer password){return teacherMapper.updateTeachPassword(username,password);}
}
