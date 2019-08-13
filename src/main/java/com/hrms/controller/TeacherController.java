package com.hrms.controller;

import com.hrms.bean.Teacher;
import com.hrms.service.TeacherService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
@Controller
@RequestMapping(value = "/hrms/teach")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    /**
     * 删除
     * @param teachId
     * @return
     */
    @RequestMapping(value = "/delTeach/{teachId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteTeach(@PathVariable("teachId") Integer teachId){
        int res = 0;
        if (teachId >=0){
            res = teacherService.deleteTeachById(teachId);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("del_teach_error", "删除异常");
        }
        return JsonMsg.success();
    }

    /**
     * 教师更改
     * @param teachId
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/updateTeach/{teachId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateTeachById(@PathVariable("teachId") Integer teachId, Teacher teacher){

        int res = 0;
        if (teachId > 0){
            res = teacherService.updateTeachById(teachId, teacher);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("update_teach_error", "教师更新失败");
        }
        return JsonMsg.success();
    }

    /**
     * 新增教师
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/addTeach", method = RequestMethod.PUT)
    @ResponseBody  //不会解析成跳转路径，写入到Http response body 中
    public JsonMsg addTeach(Teacher teacher){
        int res = teacherService.addTeach(teacher);
        if (res != 1){
            return JsonMsg.fail().addInfo("add_teach_error", "添加异常！");
        }
        return JsonMsg.success();
    }

    /**
     * 查询教师信息总页码数
     * @return
     */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages(){

        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = teacherService.getTeachCount();
        int tstu = totalItems / limit;
        int totalPages = (totalItems % limit== 0) ? tstu : tstu+1;

        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    /**
     *
     */

    @RequestMapping(value = "/getTeachById/{teachId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTeachById(@PathVariable("teachId") Integer teachId){
        Teacher teacher = null;
        if (teachId > 0){
            teacher = teacherService.getTeachById(teachId);
        }
        if (teacher != null){
            return JsonMsg.success().addInfo("teacher", teacher);
        }
        return JsonMsg.fail().addInfo("get_teach_error", "无教师信息");
    }

    /**
     * 分页查询：返回指定页数对应的数据
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getTeachList", method = RequestMethod.GET)
    public ModelAndView getTeachList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("teacherPage");
        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = teacherService.getTeachCount();
        int tstu = totalItems / limit;
        int totalPages = (totalItems % limit== 0) ? tstu : tstu+1;
        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset = (pageNo - 1)*limit;
        List<Teacher> teachers = teacherService.getTeachList(offset, limit);

        mv.addObject("teachers", teachers)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPageNo", pageNo);
        return mv;
    }

    /**
     * 查询所有教师名称
     * @return
     */
    @RequestMapping(value = "/getTeachName", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTeachName(){
        List<Teacher> teacherList = teacherService.getTeachName();
        if (teacherList != null){
            return JsonMsg.success().addInfo("teacherList", teacherList);
        }
        return JsonMsg.fail();
    }


}
