package com.shoulaxiao.controller;

import com.shoulaxiao.entity.Record;
import com.shoulaxiao.service.RecordService;
import com.shoulaxiao.util.Message;
import com.shoulaxiao.util.excel.ReadMedicalExcel;
import com.shoulaxiao.util.excel.WDWUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private RecordService recordService;


    @ResponseBody
    @RequestMapping("/getSheetHead")
    public Message getSheetHead(){
return Message.fail();
    }

    @ResponseBody
    @RequestMapping(value = "/dorecord")
    public Message insertToMongodb(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request, HttpServletResponse response){
        if (myfile==null){
            return Message.fail();
        }

        String filename=myfile.getOriginalFilename();

        long size=myfile.getSize();
        if(filename==null || ("").equals(filename) && size==0 && !WDWUtil.validateExcel(filename)){
            return Message.fail();
        }


        ReadMedicalExcel readMedicalExcel=new ReadMedicalExcel();
        long start2007 = System.currentTimeMillis();
        List<Record> list2007 = readMedicalExcel.readFromXLSX2007(myfile);

        for (Record record : list2007) {
            System.out.println(record);
        }
        recordService.insertAll(list2007);
        long end2007 = System.currentTimeMillis();
        System.out.println("读取耗时："+(end2007 - start2007) + " ms done!");
        return Message.success();
    }


    @ResponseBody
    @RequestMapping("/queryrecord")
    public Message InquireRecord(){
        List<Record> recordList=recordService.findAll();
        return Message.success().add("recordList",recordList);
    }

    @ResponseBody
    @RequestMapping("/queryrecordtest")
    public Message InquireRecordByFileName(@RequestParam("filedName") String filedName,@RequestParam("keyWord") String keyWord){
        List<Record> records=recordService.findByFiledName(filedName, keyWord);
        if (!records.isEmpty()){
            return Message.success().add("records",records);
        }else {
            return Message.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/deleterecordAll")
    public Message deleteRecordAll(){
        recordService.deleteAll();
        return Message.success();
    }

}
