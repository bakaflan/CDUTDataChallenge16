package com.shoulaxiao.util.excel;

import com.shoulaxiao.entity.Employee;
import com.shoulaxiao.entity.Record;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReadMedicalExcel {

    public static List<String> titleExcel;


    /**
     * 读取Excel2003的表头
     * @param Mfile
     * @return
     */
    public  String[] readHeaderFromXLS2003(MultipartFile Mfile){
        String[] excelTitle = null;
        CommonsMultipartFile cf=(CommonsMultipartFile)Mfile; ////获取本地存储路径
        File excelFile =new  File("D:\\fileupload");// Excel文件对象
        if (!excelFile.exists())
            excelFile.mkdirs();
        File file1=new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        try{
            cf.getFileItem().write(file1);
        }catch (Exception e){
            e.printStackTrace();
        }

        FileInputStream is = null;
        try{
            is = new FileInputStream(excelFile);
            HSSFWorkbook workbook2003 = new HSSFWorkbook(is);
            //循环读取工作表
            for (int i = 0; i < workbook2003.getNumberOfSheets(); i++) {
                HSSFSheet hssfSheet = workbook2003.getSheetAt(i);
                //*************获取表头是start*************
                HSSFRow sheetRow = hssfSheet.getRow(i);
                excelTitle = new String[sheetRow.getLastCellNum()];
                for (int k = 0; k < sheetRow.getLastCellNum(); k++) {
                    HSSFCell hssfCell = sheetRow.getCell(k);
                    excelTitle[k] = hssfCell.getStringCellValue();
//		            	System.out.println(excelTitle[k] + " ");
                }
                //*************获取表头end*************
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelTitle;
    }


    /**
     * 读取2007表头
     * @param Mfile
     * @return
     */
    public  String[] readHeaderFromXLS2007(MultipartFile Mfile){
        String[] excelTitle = null;
        CommonsMultipartFile cf=(CommonsMultipartFile)Mfile; ////获取本地存储路径
        File excelFile =new  File("D:\\fileupload");// Excel文件对象
        if (!excelFile.exists())
            excelFile.mkdirs();
        File file1=new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        try{
            cf.getFileItem().write(file1);
        }catch (Exception e){
            e.printStackTrace();
        }

        FileInputStream is = null;
        try{
            is = new FileInputStream(excelFile);
            XSSFWorkbook workbook2007 = new XSSFWorkbook(is);
            //循环读取工作表
            for (int i = 0; i < workbook2007.getNumberOfSheets(); i++) {
                XSSFSheet hssfSheet = workbook2007.getSheetAt(i);
                //*************获取表头是start*************
                XSSFRow sheetRow = hssfSheet.getRow(i);
                excelTitle = new String[sheetRow.getLastCellNum()];
                for (int k = 0; k < sheetRow.getLastCellNum(); k++) {
                    XSSFCell hssfCell = sheetRow.getCell(k);
                    excelTitle[k] = hssfCell.getStringCellValue();
//		            	System.out.println(excelTitle[k] + " ");
                }
                //*************获取表头end*************
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelTitle;
    }


    /**
     * 读取Excel2003的主表数据 （单个sheet）
     * @param Mfile
     * @return
     */
    private  List<Record> readFromXLS2003(MultipartFile Mfile) {
        // 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf=(CommonsMultipartFile)Mfile; ////获取本地存储路径
        File excelFile =new  File("D:\\fileupload");// Excel文件对象
        if (!excelFile.exists())
            excelFile.mkdirs();
        File file1=new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        try{
            cf.getFileItem().write(file1);
        }catch (Exception e){
            e.printStackTrace();
        }

        InputStream is = null;// 输入流对象
        String cellStr = null;// 单元格，最终按字符串处理
        List<Record> recordList = new ArrayList<Record>();// 返回封装数据的List
        Record record = null;// 每一个雇员信息对象
        try {

            is = new FileInputStream(excelFile);// 获取文件输入流
            HSSFWorkbook workbook2003 = new HSSFWorkbook(is);// 创建Excel2003文件对象
            HSSFSheet sheet = workbook2003.getSheetAt(0);// 取出第一个工作表，索引是0
            // 开始循环遍历行，表头不处理，从1开始
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);// 获取行对象
                record = new Record();// 实例化Student对象
                if (row == null) {// 如果为空，不处理
                    continue;
                }
                // 循环遍历单元格
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    HSSFCell cell = row.getCell(j);// 获取单元格对象
                    if (cell == null) {// 单元格为空设置cellStr为空串
                        cellStr = "";
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
                        cellStr = String.valueOf(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
                        cellStr = cell.getNumericCellValue() + "";
                    } else {// 其余按照字符串处理
                        cellStr = cell.getStringCellValue();
                    }
                    // 下面按照数据出现位置封装到bean中
                    if (j == 0) {
                        record.setSpecimen(cellStr);
                    } else if (j == 1) {
                        record.setPathology(cellStr);
                    } else if (j == 2) {
                        record.setDifferentLevel(cellStr);
                    } else if (j == 3) {
                        record.setIraits(cellStr);
                    } else if(j == 4){
                        record.setUperCut(cellStr);
                    }else if (j==5){
                        record.setLowerCut(cellStr);
                    }else if (j==6){
                        record.setBaseCut(cellStr);
                    }else if (j==7){
                        if (cellStr=="Ⅱ"){
                            cellStr="2";
                        }else {
                            cellStr="1";
                        }
                        record.setPathologyLevel(new Integer(cellStr).intValue());
                    }else if (j==8){
                        record.setTumorSize(cellStr);
                    }else if (j==9){
                        record.setInfiltration(cellStr);
                    }else if (j==10){
                        record.setLymphTransfer(cellStr);
                    }else if (j==11){
                        record.setTransferRatio(cellStr);
                    }else if (j==12){
                        record.setVascularInvasion(cellStr);
                    }else {
                        record.setNerveInvasion(cellStr);
                    }
                }
                recordList.add(record);// 数据装入List
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordList;
    }

    /**
     * 读取Excel2007的示例方法 （单个sheet）
     * @param
     * @return
     */
    public  List<Record> readFromXLSX2007(MultipartFile Mfile) {
        // 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf=(CommonsMultipartFile)Mfile; ////获取本地存储路径
        File excelFile =new  File("D:\\fileupload");// Excel文件对象
        if (!excelFile.exists())
            excelFile.mkdirs();
        File file1=new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        try{
            cf.getFileItem().write(file1);
        }catch (Exception e){
            e.printStackTrace();
        }

        InputStream is = null;// 输入流对象
        String cellStr = null;// 单元格，最终按字符串处理
        List<Record> recordList = new ArrayList<Record>();// 返回封装数据的List
        Record record = null;// 每一个雇员信息对象
        try {
            is = new FileInputStream(file1);// 获取文件输入流
//            XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2007文件对象
            org.apache.poi.ss.usermodel.Workbook workbook2007 = WorkbookFactory.create(is);
//            XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
            org.apache.poi.ss.usermodel.Sheet sheet = workbook2007.getSheetAt(0);

            // 开始循环遍历行，表头不处理，从1开始
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                record = new Record();// 实例化Student对象
//            	HSSFRow row = sheet.getRow(i);// 获取行对象
                Row row = sheet.getRow(i);// 获取行对象
                if (row == null) {// 如果为空，不处理
                    continue;
                }
                // 循环遍历单元格
                for (int j = 0; j < row.getLastCellNum(); j++) {
//                    XSSFCell cell = row.getCell(j);// 获取单元格对象
                    Cell cell = row.getCell(j);// 获取单元格对象
                    if (cell == null) {// 单元格为空设置cellStr为空串
                        cellStr = "";
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
                        cellStr = String.valueOf(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
                        cellStr = cell.getNumericCellValue() + "";
                    } else {// 其余按照字符串处理
                        cellStr = cell.getStringCellValue();
                    }
                    // 下面按照数据出现位置封装到bean中
                    if (j == 0) {
                        record.setSpecimen(cellStr);
                    } else if (j == 1) {
                        record.setPathology(cellStr);
                    } else if (j == 2) {
                        record.setDifferentLevel(cellStr);
                    } else if (j == 3) {
                        record.setIraits(cellStr);
                    } else if(j == 4){
                        record.setUperCut(cellStr);
                    }else if (j==5){
                        record.setLowerCut(cellStr);
                    }else if (j==6){
                        record.setBaseCut(cellStr);
                    }else if (j==7){
                        if (cellStr=="Ⅱ"){
                            cellStr="2";
                        }else {
                            cellStr="1";
                        }
                        record.setPathologyLevel(new Integer(cellStr).intValue());
                    }else if (j==8){
                        record.setTumorSize(cellStr);
                    }else if (j==9){
                        record.setInfiltration(cellStr);
                    }else if (j==10){
                        record.setLymphTransfer(cellStr);
                    }else if (j==11){
                        record.setTransferRatio(cellStr);
                    }else if (j==12){
                        record.setVascularInvasion(cellStr);
                    }else {
                        record.setNerveInvasion(cellStr);
                    }
                }
                recordList.add(record);// 数据装入List
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
        }finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordList;
    }

    /**
     * 读取Excel的示例方法 （多个sheet）
     * @param filePath
     * @return
     */
    public List<Employee> readMoreSheetFromXLS(String filePath){
        List<Employee> employeeList = new ArrayList<Employee>();
        String cellStr = null;//单元格，最终按字符串处理
        //创建来自excel文件的输入流
        try {
            FileInputStream is = new FileInputStream(filePath);
            //创建WorkBook实例
            Workbook workbook = null;
            if (filePath.toLowerCase().endsWith("xls")) {//2003
                workbook = new HSSFWorkbook(is);
            }else if(filePath.toLowerCase().endsWith("xlsx")){//2007
                workbook = WorkbookFactory.create(is);
            }
            //获取excel文件的sheet数量
            int numOfSheets = workbook.getNumberOfSheets();
            //挨个遍历sheet
            for (int i = 0; i < numOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                //挨个遍历sheet的每一行
                for (Iterator<Row> iterRow = sheet.iterator(); iterRow.hasNext();) {
                    Row row = iterRow.next();
                    Employee employee = new Employee();
                    int j = 0;//标识位，用于标识第几列
                    //挨个遍历每一行的每一列
                    for (Iterator<Cell> cellIter = row.cellIterator();cellIter.hasNext();) {
                        Cell cell = cellIter.next();//获取单元格对象
                        if (j == 0) {
                            if (cell == null) {// 单元格为空设置cellStr为空串
                                cellStr = "";
                            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
                                cellStr = String.valueOf(cell.getBooleanCellValue());
                            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
                                cellStr = cell.getNumericCellValue() + "";
                            } else {// 其余按照字符串处理
                                cellStr = cell.getStringCellValue();
                            }
                            employee.setName(cellStr);
                            j ++;
                        }
//						employee.setGender(cellStr); j == 1
//						employee.setAge(new Double(cellStr).intValue()); j == 2
//						employee.setDepartment(cellStr); j == 3
//						employee.setSalary(new Double(cellStr).intValue());  j == 4
//						employee.setDate(cellStr); j == 5
                        employeeList.add(employee);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
