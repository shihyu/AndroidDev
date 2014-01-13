package com.readlog;

import android.app.Activity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.widget.*;
import com.readlog.R;


public class ReadlogActivity extends Activity {
    /** Called when the activity is first created. */
  public static TextView mtextview;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mtextview=(TextView)findViewById(R.id.multiAutoCompleteTextView1);
                getLog();
                //mtextview.append(str);
	}

        public static void getLog()
        {
            System.out.println("--------func start--------"); // 方法启动
            try
            {
                ArrayList<String> cmdLine=new ArrayList<String>();   //设置命令   logcat -d 读取日志
                cmdLine.add("logcat");
                cmdLine.add("-d");
                
                ArrayList<String> clearLog=new ArrayList<String>();  //设置命令  logcat -c 清除日志
                clearLog.add("logcat");
                clearLog.add("-c");
                
                Process process=Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));   //捕获日志
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));    //将捕获内容转换为BufferedReader
                

//                Runtime.runFinalizersOnExit(true);
                String str=null;
                while((str=bufferedReader.readLine())!=null)    //开始读取日志，每次读取一行
                {
                    Runtime.getRuntime().exec(clearLog.toArray(new String[clearLog.size()]));  //清理日志....这里至关重要，不清理的话，任何操作都将产生新的日志，代码进入死循环，直到bufferreader满
                   
                  //  System.out.println(str);    //输出，在logcat中查看效果，也可以是其他操作，比如发送给服务器..
                    str=str+("\n");
                  if(  str.indexOf("content://mms-sms")>0)
                   mtextview.append(str);
                  //if(  str.indexOf("sendData")>0)
                    //  mtextview.append(str);
                }
                if(str==null)
                {
                    System.out.println("--   is null   --");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("--------func end--------");
        }
    
}
