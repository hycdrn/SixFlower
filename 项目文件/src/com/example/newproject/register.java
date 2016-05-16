package com.example.newproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class register extends Activity{
	private EditText etx; //用户名
	private EditText etx2;//密码
	private EditText etx3;//确认密码
	private Button btn; //确定注册
	 SQLiteDatabase db;
	 
	 public void onCreate(Bundle savedInstanceState) {  
		 
	        super.onCreate(savedInstanceState);  
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.three);
	        
	        etx=(EditText)findViewById(R.id.user);
	        etx2=(EditText)findViewById(R.id.password);
	        etx3=(EditText)findViewById(R.id.pw2);
	        btn=(Button)findViewById(R.id.register);
	       
	        btn.setOnClickListener(new registerListener());	
	    
	       
	    }  
	 class registerListener implements OnClickListener{
		 

				@Override
				public void onClick(View arg0) {
				      String name = etx.getText().toString();  
		                String password = etx2.getText().toString();  
		                String pw2=etx3.getText().toString();
		                if (name.equals("") && password.equals("")) 
		                {
		                	new AlertDialog.Builder(register.this)  
	                        .setTitle("帐号密码不能为空").setMessage("帐号密码不能为空")  
	                        .setPositiveButton("确定", null).show(); 
		                }
		                else if (!password.equals(pw2))
		                {
		                	new AlertDialog.Builder(register.this)  
	                        .setTitle("错误").setMessage("帐号密码不一致")  
	                        .setPositiveButton("确定", null).show(); 
		                	System.out.print("b");
		                }
		                else if (addUser(name, password)) {  
		                        DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {  
		                           
		                            public void onClick(DialogInterface dialog,  
		                                    int which) {  
		                                // TODO Auto-generated method stub  
		                                // 跳转到登录界面  
		                                Intent in = new Intent();  
		                                in.setClass(register.this,  
		                                        main.class);  
		                                startActivity(in);  
		                                // 销毁当前activity  
		                                register.this.onDestroy();  
		                            }  
		                        };  
		                        new AlertDialog.Builder(register.this)  
		                                .setTitle("注册成功").setMessage("注册成功")  
		                                .setPositiveButton("确定", ss).show();  
		                       
		  
		                    } else {  
		                        new AlertDialog.Builder(register.this)  
		                                .setTitle("注册失败").setMessage("注册失败")  
		                                .setPositiveButton("确定", null).show();  
		                        
		                    }  
		                
		            } 
				
	        	
	 }
	 public Boolean addUser(String name, String password) {  
	        String str = "insert into user values(?,?) ";  
	        login main = new login();  
	        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()  
	                + "/test.dbs", null);  
	        main.db = db;  
	        try {  
	            db.execSQL(str, new String[] { name, password });  
	            return true;  
	        } catch (Exception e) {  
	            main.createDb();  
	        }  
	        return false;  
	    }  
	  protected void onDestroy() {  
	        // TODO Auto-generated method stub  
	        super.onDestroy();  
	        db.close();  
	    }
	
}
