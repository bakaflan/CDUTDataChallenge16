package com.shoulaxiao.controller;

import com.shoulaxiao.entity.User;
import com.shoulaxiao.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private MongoService mongoService;

    // 查询
    @RequestMapping(value = "testMongo")
    public String viewAll10() {
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        List<User> userlist=new ArrayList();
        for (int i=0;i<100;i++){
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
            User user = new User();
            user.setName(sb.toString());
            user.setAge(i);
            userlist.add(user);
        }

        mongoService.insertAll(userlist);

        return "success";
    }
}
