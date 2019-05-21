package Excel;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonTest {

    @Test
    public void PythonTest(){
        System.out.println("开始执行python程序");
        String path="E:\\数字媒体技术\\数据挑战赛\\1000records.csv";
        String[] arg=new String[]{
                "python","E:\\Vedio\\test.py",path};
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
        System.out.println("end");
    }

}
