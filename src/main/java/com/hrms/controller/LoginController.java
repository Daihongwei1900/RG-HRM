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
    public JsonMsg dologin(HttpServletRequest request ) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Student stuByName = studentService.getStuByName(username);
        Teacher teachByName = teacherService.getTeachByName(username);
        HttpSession session = request.getSession();
        if (stuByName != null) {
            String stuPassword = stuByName.getStuPassword().toString();
            if (stuPassword.equals(password)) {
                session.setAttribute("user_msg", "欢迎" + username + "同学");
                return JsonMsg.success();
            } else {
                return JsonMsg.fail().addInfo("login_error", username + "同学，你的密码输入错误！");
            }
        } else if (teachByName != null) {
            String teachPassword = teachByName.getTeachPassword().toString();
            if (teachPassword.equals(password)) {
                session.setAttribute("user_msg", "欢迎" + username + "老师");
                return JsonMsg.success();
            } else {
                return JsonMsg.fail().addInfo("login_error", username + "老师，你的密码输入错误！");
            }
        } else
            return JsonMsg.fail().addInfo("login_error", "改用户不存在，请重新输入！");
    }

    @RequestMapping(value = "/getUserMsg",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getUserMsg(HttpServletRequest request){
        String user_msg = (String) request.getSession().getAttribute("user_msg");
        int length = user_msg.length();
        String username=user_msg.substring(2,length-2);
        String type = user_msg.substring(length-2,length);
        return JsonMsg.success().addInfo("username",username).addInfo("type",type);
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
