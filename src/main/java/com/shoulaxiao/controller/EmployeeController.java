package com.shoulaxiao.controller;

import com.shoulaxiao.entity.Employee;
import com.shoulaxiao.service.EmployeeService;
import com.shoulaxiao.util.Message;
import com.shoulaxiao.util.excel.ReadExcel;
import com.shoulaxiao.util.excel.WDWUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static String xlsx2007 = "E:\\数字媒体技术\\数据挑战赛\\testData.xlsx";

    @ResponseBody
    @RequestMapping(value = "/doemployee")
    public Message insertToMongodb(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request, HttpServletResponse response){
        if (myfile==null){
          return Message.fail();
        }

        String filename=myfile.getOriginalFilename();

        long size=myfile.getSize();
        if(filename==null || ("").equals(filename) && size==0 && !WDWUtil.validateExcel(filename)){
            return Message.fail();
        }


        ReadExcel readExcel=new ReadExcel();
        long start2007 = System.currentTimeMillis();
        List<Employee> list2007 = readExcel.readFromXLSX2007(myfile);
        for (Employee employee : list2007) {
            System.out.println(employee);
        }
        employeeService.insertAll(list2007);
        long end2007 = System.currentTimeMillis();
        System.out.println("读取耗时："+(end2007 - start2007) + " ms done!");
        return Message.success();
    }


    @ResponseBody
    @RequestMapping("/deletefromdb")
    public Message deletefromDB(){
        employeeService.deleteAll();
        return Message.success();
    }
}
