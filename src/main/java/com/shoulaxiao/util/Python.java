package com.shoulaxiao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Python {

    public void PythonRun(String file_python){
        System.out.println("开始执行python程序");
        String[] arg=new String[]{
                "python","E:\\Project\\Python\\Data\\Main.py",file_python};
        Process pr;
        try {
            pr=Runtime.getRuntime().exec(arg);
            BufferedReader in=new BufferedReader(new InputStreamReader(pr.getInputStream(),"gb2312"));
            String line;
            while ((line=in.readLine())!=null){
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
