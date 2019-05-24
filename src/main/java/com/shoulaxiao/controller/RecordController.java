package com.shoulaxiao.controller;

import com.shoulaxiao.entity.PageInfo;
import com.shoulaxiao.entity.Record;
import com.shoulaxiao.service.RecordService;
import com.shoulaxiao.util.Message;
import com.shoulaxiao.util.Python;
import com.shoulaxiao.util.excel.ReadMedicalExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private RecordService recordService;


    /**
     * 获取上传的xsl文件,调用python,读取并插入数据库
     *
     * @param myfile
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/doRecord")
    public Message insertToMongodb(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (myfile.isEmpty()) {
            return Message.fail();
        }else
        {
        String file_name = myfile.getOriginalFilename();
        String file_path=request.getServletContext().getRealPath("/excel/");
        File excel_path=new File(file_path,file_name);
        if (!excel_path.getParentFile().exists()){
            excel_path.getParentFile().mkdirs();
        }

        myfile.transferTo(new File(file_path+File.separator+file_name));


        ReadMedicalExcel readMedicalExcel = new ReadMedicalExcel();
        long start2007 = System.currentTimeMillis();

        String test=file_path+file_name;
        String path_excel=test.replaceAll("\\\\","\\\\\\\\");

        Python python=new Python();
        python.PythonRun(path_excel);
        String line="E:\\Project\\Python\\Data\\Output.xlsx";
        List<Record> list2007 = readMedicalExcel.readFromXLSX2007(line);

        recordService.insertAll(list2007);
        long end2007 = System.currentTimeMillis();
        System.out.println("读取耗时：" + (end2007 - start2007) + "ms done!");
        return Message.success();
        }
    }


    /**
     * 查询所有医疗记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/record")
    public Message InquireRecord(@RequestParam(value = "page",defaultValue = "1") Integer page) {
        if (page<=0){
            page=1;
        }
        Page<Record> pageList=recordService.findList(page);
        return Message.success().add("pageList",pageList);
    }

    /**
     * 根据查询条件查询
     *
     * @param filedName 字段名称
     * @param keyWord   搜索的关键字
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryRecord")
    public Message InquireRecordByFileName(@RequestParam("filedName") String filedName, @RequestParam("keyWord") String keyWord) {
        List<Record> records = recordService.findByFiledName(filedName, keyWord);
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPagesize(10);
        long count=records.size();
        Page<Record> listPage=new PageImpl<Record>(records,pageInfo,count);
        if (listPage.getTotalPages()!=0) {
            return Message.success().add("records", records);
        } else {
            return Message.fail();
        }
    }


    /**
     * 删除所有操作
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRecordAll")
    public Message deleteRecordAll() {

        recordService.deleteAll();
        return Message.success();
    }
}
