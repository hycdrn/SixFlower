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
    private Button btn;    //��¼
    private EditText etx;  //�ʺ�
    private Button btn2;   //ע��
    private EditText etx2; //����
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
                //����Intent��class���ԣ���ת��SecondActivity  
                intent.setClass(login.this,register.class);  
                //����Activity  
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
    			new AlertDialog.Builder(login.this).setTitle("error").setMessage("�ʺ����벻����Ϊ��").setPositiveButton("ȷ��", null).show();
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
                     new AlertDialog.Builder(login.this).setTitle("����")  
                     .setMessage("�ʺŻ��������").setPositiveButton("ȷ��", null)  
                     .show();  
                     return false;  
                 }else{  
                	 DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() 
                	 {  
                         public void onClick(DialogInterface dialog,  
                                 int which) {  
                             // TODO Auto-generated method stub  
                             // ��ת����¼����  
                             Intent in = new Intent();  
                             in.setClass(login.this,  
                                     main.class);  
                             startActivity(in);  
                             // ���ٵ�ǰactivity  
                              
                         }};    
                         new AlertDialog.Builder(login.this)  
                         .setTitle("!!!").setMessage("��¼�ɹ�")  
                         .setPositiveButton("ȷ��", ss).show();  
                     return true;  
                 }  
                   
             }catch(SQLiteException e){  
                 createDb();  
             }  
             return false;  
         }  
       
     }  
     // �������ݿ���û���  
     public void createDb() {  
         db.execSQL("create table user( name varchar(30) primary key,password varchar(30))");  
     }  
     protected void onDestroy() {  
         // TODO Auto-generated method stub  
         super.onDestroy();  
         db.close();  
     }  

    }
