package com.elec.mall.controller;

import com.elec.mall.pojo.User;
import com.elec.mall.service.IUserService;
import com.elec.mall.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/checkUserLogin")
    @ResponseBody
    public JSONResult checkUserLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user == null ? JSONResult.error() : JSONResult.ok();
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user == null) {
            return JSONResult.error("登录失败");
        } else {
            session.setAttribute("user", user);
            return JSONResult.ok("登录成功");
        }
    }

    @RequestMapping("/getLoginPage")
    public String getLoginPage() {
        return "login";
    }
}
