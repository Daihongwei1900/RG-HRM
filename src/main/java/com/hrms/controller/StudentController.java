package com.hrms.controller;

import com.hrms.bean.Student;
import com.hrms.service.StudentService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
@Controller
@RequestMapping(value = "/hrms/stu")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 学生删除操作
     * @param stuId
     * @return
     */


    @RequestMapping(value = "/deleteStu/{stuId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteStu(@PathVariable("stuId") Integer stuId){
        int res = 0;
        if (stuId > 0){
            res = studentService.deleteStuById(stuId);
        }
        if (res != 1){
            return JsonMsg.fail().addInfo("stu_del_error", "学生删除异常");
        }
        return JsonMsg.success();
    }

    /**
     * 更改学生信息
     * @param stuId
     * @param student
     * @return
     */
    @RequestMapping(value ="/updateStu/{stuId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateStu(@PathVariable("stuId") Integer stuId, Student student){
        int res = studentService.updateStuById(stuId, student);
        if (res != 1){
            return JsonMsg.fail().addInfo("stu_update_error", "更改异常");
        }
        return JsonMsg.success();
    }

    /**
     * 查询输入的学生姓名是否重复
     * @param stuName
     * @return
     */
    @RequestMapping(value = "/checkStuExists", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkStuExists(@RequestParam("stuName") String stuName){
        //对输入的姓名与邮箱格式进行验证
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!stuName.matches(regName)){
            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Student student = studentService.getStuByName(stuName);
        if (student != null){
            return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
        }else {
            return JsonMsg.success();
        }
    }
  @RequestMapping(value = "/checkStuIdExists",method = RequestMethod.GET)
  @ResponseBody
    public JsonMsg checkStuIdExists(@RequestParam("stuId") Integer stuId){
      Student student = studentService.getStuById(stuId);
      if (student!=null){
         return JsonMsg.fail().addInfo("stuId_reg_error","该学号已经存在！");
      }
      else {
          return JsonMsg.success();
      }

    }

    /**
     * 新增记录后，查询最新的页数
     * @return
     */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage(){
        int totalItems = studentService.getStuCount();
        //获取总的页数
        int tstu = totalItems / 5;
        int totalPages = (totalItems % 5 == 0) ? tstu : tstu+1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    /**
     * 新增学生
     * @param student 新增的学生信息
     * @return
     */
    @RequestMapping(value = "/addStu", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addStu(Student student){
        int res = studentService.addStu(student);
        if (res == 1){
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 根据id查询学生信息
     * @param stuId
     * @return
     */
    @RequestMapping(value = "/getStuById/{stuId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getStuById(@PathVariable("stuId") Integer stuId){
        Student student = studentService.getStuById(stuId);
        if (student != null){
            return JsonMsg.success().addInfo("student", student);
        }else {
            return JsonMsg.fail();
        }

    }
    /**
     * 查询
     * @param pageNo 查询指定页码包含的数据
     * @return
     */
    @RequestMapping(value = "/getStuList", method = RequestMethod.GET)
    public ModelAndView getStu(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
        ModelAndView mv = new ModelAndView("studentPage");
        int limit = 5;
        // 记录的偏移量(即从第offset行记录开始查询)，
        // 如第1页是从第1行(offset=(21-1)*5=0,offset+1=0+1=1)开始查询；
        // 第2页从第6行(offset=(2-1)*5=5,offset+1=5+1=6)记录开始查询
        int offset = (pageNo-1)*limit;
        //获取指定页数包含的学生信息
        List<Student> students = studentService.getStuList(offset, limit);
        //获取总的记录数
        int totalItems = studentService.getStuCount();
        //获取总的页数
        int tstu = totalItems / limit;
        int totalPages = (totalItems % limit == 0) ? tstu : tstu+1;
        //当前页数
        int curPage = pageNo;

        //将上述查询结果放到Model中，在JSP页面中可以进行展示
        mv.addObject("students", students)
                .addObject("totalItems", totalItems)
                .addObject("totalPages", totalPages)
                .addObject("curPage", curPage);
        return mv;
    }





}
