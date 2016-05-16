package com.example.newproject;  
 
import android.app.Activity;  
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;  
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.Window;
import android.widget.Button;  
import android.widget.EditText;  
 
public class login extends Activity {  
    private Button btn;    //登录
    private EditText etx;  //帐号
    private Button btn2;   //注册
    private EditText etx2; //密码
    public static SQLiteDatabase db;
    
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.frist);  
 
        btn = (Button)findViewById(R.id.Button1);  
        etx = (EditText)findViewById(R.id.EditText1);  
        btn2=(Button)findViewById(R.id.Button2);
        etx2=(EditText)findViewById(R.id.EditText2);
        db = SQLiteDatabase.openOrCreateDatabase(login.this.getFilesDir().toString()  
                + "/test.dbs", null);
        btn.setOnClickListener(new LoginListener());
        btn2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent();  
                //设置Intent的class属性，跳转到SecondActivity  
                intent.setClass(login.this,register.class);  
                //启动Activity  
                startActivity(intent);  
				
			}
        	
        });
    }  
  
    class LoginListener implements OnClickListener {

    	@Override
    	public void onClick(View v) {
    		String name=etx.getText().toString();
    		String password=etx2.getText().toString();
    		if(name.equals("")||password.equals(""))
    		{
    			new AlertDialog.Builder(login.this).setTitle("error").setMessage("帐号密码不允许为空").setPositiveButton("确定", null).show();
    		}
    		else{
    			 isUserinfo(name, password); 
    		}
    		
    		
    	}
    	 public Boolean isUserinfo(String name, String pwd) {  
             try{  
                 String str="select * from user where name=? and password=?";  
                 Cursor cursor = db.rawQuery(str, new String []{name,pwd});  
                 if(cursor.getCount()<=0){  
                     new AlertDialog.Builder(login.this).setTitle("错误")  
                     .setMessage("帐号或密码错误！").setPositiveButton("确定", null)  
                     .show();  
                     return false;  
                 }else{  
                	 DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() 
                	 {  
                         public void onClick(DialogInterface dialog,  
                                 int which) {  
                             // TODO Auto-generated method stub  
                             // 跳转到登录界面  
                             Intent in = new Intent();  
                             in.setClass(login.this,  
                                     main.class);  
                             startActivity(in);  
                             // 销毁当前activity  
                              
                         }};    
                         new AlertDialog.Builder(login.this)  
                         .setTitle("!!!").setMessage("登录成功")  
                         .setPositiveButton("确定", ss).show();  
                     return true;  
                 }  
                   
             }catch(SQLiteException e){  
                 createDb();  
             }  
             return false;  
         }  
       
     }  
     // 创建数据库和用户表  
     public void createDb() {  
         db.execSQL("create table user( name varchar(30) primary key,password varchar(30))");  
     }  
     protected void onDestroy() {  
         // TODO Auto-generated method stub  
         super.onDestroy();  
         db.close();  
     }  

    }
