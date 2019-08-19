package com.hrms.mapper;

import com.hrms.bean.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
public interface TeacherMapper {

    String TABLE_NAME = "tbl_teach";
    String INSERT_FIELDS = "teach_name, teach_title";
    String SELECT_FIELDS = "teach_id as 'teachId', " +
            "teach_name as 'teachName', " +
            "teach_title as 'teachTitle'";


    /**
     * =================================删除============================================
     */
    @Delete({"DELETE FROM", TABLE_NAME, "WHERE teach_id=#{teachId}"})
    int deleteTeachById(@Param("teachId") Integer teachId);

    /**
     * =================================更改============================================
     */
    @Update({"UPDATE",TABLE_NAME,"SET teach_name=#{teacher.teachName},teach_title=#{teacher.teachTitle} where teach_id=#{teachId}"})
    int updateTeachById(@Param("teachId") Integer teachId,
                       @Param("teacher") Teacher teacher);

    /**
     * =================================新增============================================
     */
    @Insert({"INSERT INTO",TABLE_NAME, "(", INSERT_FIELDS ,",teach_id) " +
            "VALUES(#{teacher.teachName}, #{teacher.teachTitle},#{teacher.teachId})" })
    int insertTeach(@Param("teacher") Teacher teacher);

    /**
     * =================================查询============================================
     */
    @Select({"SELECT", SELECT_FIELDS, "FROM", TABLE_NAME, "WHERE teach_id=#{teachId}" })
    Teacher selectOneById(@Param("teachId") Integer teachId);
    @Select({"SELECT", SELECT_FIELDS, "FROM", TABLE_NAME, "WHERE teach_title=#{teachTitle}" })
    Teacher selectOneByTitle(@Param("teachTitle") String teachTitle);
    @Select({"SELECT", SELECT_FIELDS,",teach_password as 'teachPassword' FROM", TABLE_NAME, "WHERE teach_name=#{teachName}" })
    Teacher selectOneByName(@Param("teachName") String teachName);
    @Select({"SELECT", SELECT_FIELDS, "FROM", TABLE_NAME})
    List<Teacher> selectTeachList();

    @Select({"SELECT",SELECT_FIELDS,"FROM",TABLE_NAME,"limit #{offset},${limit}"})
    List<Teacher> selectTeachsByLimitAndOffset(@Param("offset") Integer offset,
                                              @Param("limit") Integer limit);

    @Select({"SELECT COUNT(teach_id) FROM", TABLE_NAME,
            "WHERE teachTitle = #{teachTitle} OR teachName = #{teachName}"})
    int checkTeachsExistsByNameAndtitle(@Param("teachTitle") String teachTitle,
                                        @Param("teachName") String teachName);

    @Select({"SELECT COUNT(*) FROM", TABLE_NAME})
    int countTeachs();

    @Update({"UPDATE",TABLE_NAME,"SET teach_password =#{password} where teach_name =#{username}"})
    int updateTeachPassword(@Param("username") String username,@Param("password") Integer password);
}
