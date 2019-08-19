package com.hrms.test;

import com.hrms.bean.Teacher;
import com.hrms.mapper.TeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class TeacherMapperTest {
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void insertTeachTest(){
        Teacher teacher = new Teacher(3, "黎明3", "测试部");
        int res = teacherMapper.insertTeach(teacher);
        System.out.println(res);
    }

    @Test
    public void updateTeachTest(){
        Teacher teacher = new Teacher(null, "Tomsom", "研发部");
        int res = teacherMapper.updateTeachById(0, teacher);
        System.out.println(res);
    }

    @Test
    public void deleteTeachTest(){
        int res = teacherMapper.deleteTeachById(7);
        System.out.println(res);
    }

    @Test
    public void selectOneByIdTest(){
        Teacher teacher = teacherMapper.selectOneById(1);
        System.out.println(teacher);
    }

    @Test
    public void selectOneByTitleTest(){
        Teacher teacher = teacherMapper.selectOneByTitle("马云");
        System.out.println(teacher);
    }

    @Test
    public void selectOneByNameTest(){
        Teacher teacher = teacherMapper.selectOneByName("CEO");
        System.out.println(teacher);
    }

    @Test
    public void selectTeachListTest(){
        List<Teacher> teacherList = teacherMapper.selectTeachList();
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.println(teacherList.get(i));
        }
    }

    @Test
    public void selectTeachsByLimitAndOffsetTest(){
        List<Teacher> teachers = teacherMapper.selectTeachsByLimitAndOffset(2,5);
        System.out.println(teachers.size());
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(teachers.get(i));
        }
    }

    @Test
    public void countTeachsTest(){
        int count = teacherMapper.countTeachs();
        System.out.println(count);
    }
    @Test
    public void updateTeachPassword(){
        int res = teacherMapper.updateTeachPassword("admin", 111111);
    }
}
