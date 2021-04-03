package edu.fzu.eduservice.controller;

import edu.fzu.commonutils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JohnCarraway
 * @create 2021-01-04 23:08
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    // login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(){
        return Result.ok().add("token", "admin");
    }


    // info https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Result info(){
        return Result.ok().add("roles", "[admin]").add("name", "JohnRambo").add("avatar", "https://edu-guli-0514.oss-cn-beijing.aliyuncs.com/2021/01/03/2664f7ed351d455e81010abde70d150e2.jpg");
    }
}
