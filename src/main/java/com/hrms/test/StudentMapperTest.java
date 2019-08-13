package com.hrms.test;

import com.hrms.bean.Student;
import com.hrms.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    //用来批量插入操作
    @Autowired
    SqlSession sqlSession;

    /**
     * 单条记录插入
     */
    @Test
    public void insertOneTest(){
        Student student = new Student(1, "aa", "aa@qq.com", "男", 2);
        int res = studentMapper.insertOne(student);
        System.out.println(res);
    }

    /**
     * 批量插入
     */
    @Test
    public void insertStusBatchTest(){
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        for (int i = 1; i < 200; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5);
            studentMapper.insertOne(new Student(i, "name_"+uid, uid+"@qq.com",  i%2==0? "F":"M", i%6+1));

        }
    }

    @Test
    public void updateOneByIdTest(){
        Student student =
                new Student(1, "aa", "aa@qq.com", "女", 3);
        int res = studentMapper.updateOneById(1, student);
        System.out.println(res);
    }

    @Test
    public void selectOneByIdTest(){
        Student student = studentMapper.selectOneById(1);
        System.out.println(student);
    }

    @Test
    public void selectOneByNameTest(){
        Student student = studentMapper.selectOneByName("name_65083");
        System.out.println(student);
    }

    @Test
    public void selectWithTeachByIdTest(){
        Student student = studentMapper.selectWithTeachById(2);
        System.out.println(student);
    }

    @Test
    public void countStusTest(){
        System.out.println(studentMapper.countStus());
    }

    @Test
    public void selectByLimitAndOffsetTest(){
        List<Student> list = studentMapper.selectByLimitAndOffset(5, 10);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void deleteOneByIdTest(){
        int res = studentMapper.deleteOneById(201);
        System.out.println(res);

    }

}
