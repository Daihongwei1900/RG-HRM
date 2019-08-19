package com.hrms.controller;

import com.hrms.service.StudentService;
import com.hrms.service.TeacherService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Daihongwei
 * @date 2019/8/1.
 */
@Controller
@RequestMapping(value = "/hrms/main")
public class MainController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    /**
     * 更改登录用户密码
     * @param username
     * @param password
     * @return
     */
        @RequestMapping(value = "/updatePassword",method = RequestMethod.PUT)
        @ResponseBody
        public JsonMsg updatePassword(@RequestParam(value ="username") String username, @RequestParam(value = "password") Integer password, HttpServletRequest request){
            HttpSession session = request.getSession();
            String user_msg = (String) session.getAttribute("user_msg");
            if (user_msg.contains("老师")){
                teacherService.updateTeachPassword(username, password);
               return JsonMsg.success();
            }else if (user_msg.contains("同学")){
                studentService.updateStuPassword(username,password);
                session.removeAttribute("user_msg");
                return JsonMsg.success();
            }else {
                return JsonMsg.fail().addInfo("update_password_msg","修改失败");
            }
        }
}
