package com.hrms.controller;

import com.hrms.bean.Student;
import com.hrms.bean.Teacher;
import com.hrms.service.StudentService;
import com.hrms.service.TeacherService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Daihongwei
 * @date 2019/8/13.
 */
@Controller
@RequestMapping(value = "/hrms")
public class LoginController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    /**
     * 登录：跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 对登录页面输入的用户名和密码做简单的判断
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg dologin(HttpServletRequest request ){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student stuByName = studentService.getStuByName(username);
        String stuPassword="111111";
        String teachPassword ="888888";
        Teacher teachByName = teacherService.getTeachByName(username);
        HttpSession session = request.getSession();
        if (stuByName!=null&&stuPassword.equals(password)){
           session.setAttribute("user_msg","欢迎"+username+"同学");
            return JsonMsg.success();
        }else if(teachByName!=null&&teachPassword.equals(password)){
            session.setAttribute("user_msg","欢迎"+username+"老师");
            return JsonMsg.success();
        }else {
            return JsonMsg.fail().addInfo("login_error", "输入账号用户名与密码不匹配，请重新输入！");
        }
//        if (!"admin1234".equals(username + password)){
//            return JsonMsg.fail().addInfo("login_error", "输入账号用户名与密码不匹配，请重新输入！");
    }

    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * 退出登录：从主页面跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "login";
    }






}
