package com.hrms.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.service.StudentService;
import com.hrms.service.TeacherService;
import com.hrms.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //提取url以及末尾追加的数字
        String uri = httpServletRequest.getRequestURI();
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(uri);
        String Id = m.replaceAll("").trim();
        //提出登录用户的信息
        HttpSession session = httpServletRequest.getSession();
        String user_msg = (String) session.getAttribute("user_msg");
        String username = user_msg.substring(2, user_msg.length() - 2);
        String type = user_msg.substring(user_msg.length() - 2, user_msg.length() - 0);

        //设置错误信息的返回对象
        httpServletResponse.reset();
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream pw = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        if (username.equals("admin")){
            return true;
        }
        if (type.equals("老师")) {
            String teachId = teacherService.getTeachByName(username).getTeachId().toString();
            if (uri.indexOf("teach") >= 0 && !teachId.equals(Id)) {
                mapper.writeValue(pw, JsonMsg.limit());
                pw.flush();
                pw.close();
                System.out.println(1);
                return false;
            } else {
                System.out.println(2);
                return true;
            }
        }
        if (type.equals("同学")) {
            String stuId = studentService.getStuByName(username).getStuId().toString();
            if (uri.indexOf("Stu") >= 0 && stuId.equals(Id)) {
                System.out.println(3);
                return true;
            } else {
                mapper.writeValue(pw, JsonMsg.limit());
                pw.flush();
                pw.close();
                return false;
            }
        } else {
            mapper.writeValue(pw, JsonMsg.limit());
            pw.flush();
            pw.close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
