package com.shoulaxiao.util.excel;

public class WDWUtil {
    /**
     * @描述：是否是2003的excel，返回true是2003
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @描述：是否是2007的excel，返回true是2007
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static boolean isCVS(String filePth){
        return filePth.matches("^.+\\.(?i)(csv)$");
    }

    /**
     * 验证是否是EXCEL文件
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath){
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath)||isCVS(filePath))){
            return false;
        }
        return true;
    }
}
