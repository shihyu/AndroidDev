package com.ptrace;

import com.ptrace.R;
import java.io.DataOutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PtraceActivity extends Activity {
    /** Called when the activity is first created. */
	private Button mybutton;
    private TextView  mytextview; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Log.v("asd","ad");
         mybutton = 	(Button)findViewById(R.id.button1);
         mytextview=(TextView)findViewById(R.id.textView1);
         String apkRoot="chmod 777 "+getPackageCodePath();
         	       RootCommand(apkRoot);
         mybutton.setOnClickListener(
         	    new Button.OnClickListener() {
         	        public void onClick(View v)
         	        { 
         	        	
         	         mytextview.setText(attach());
         	         //  main();
         	        }
         	        }
         	          	    );   
        
            }
    
   
	
	public native String attach();
    static {
        System.loadLibrary("ptrace");
    }
    public static boolean RootCommand(String command)
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e)
        {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
            	 
            	
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }

}
//public class SystemManager extends Activity
