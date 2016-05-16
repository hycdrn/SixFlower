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
	private EditText etx; //�û���
	private EditText etx2;//����
	private EditText etx3;//ȷ������
	private Button btn; //ȷ��ע��
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
	                        .setTitle("�ʺ����벻��Ϊ��").setMessage("�ʺ����벻��Ϊ��")  
	                        .setPositiveButton("ȷ��", null).show(); 
		                }
		                else if (!password.equals(pw2))
		                {
		                	new AlertDialog.Builder(register.this)  
	                        .setTitle("����").setMessage("�ʺ����벻һ��")  
	                        .setPositiveButton("ȷ��", null).show(); 
		                	System.out.print("b");
		                }
		                else if (addUser(name, password)) {  
		                        DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {  
		                           
		                            public void onClick(DialogInterface dialog,  
		                                    int which) {  
		                                // TODO Auto-generated method stub  
		                                // ��ת����¼����  
		                                Intent in = new Intent();  
		                                in.setClass(register.this,  
		                                        main.class);  
		                                startActivity(in);  
		                                // ���ٵ�ǰactivity  
		                                register.this.onDestroy();  
		                            }  
		                        };  
		                        new AlertDialog.Builder(register.this)  
		                                .setTitle("ע��ɹ�").setMessage("ע��ɹ�")  
		                                .setPositiveButton("ȷ��", ss).show();  
		                       
		  
		                    } else {  
		                        new AlertDialog.Builder(register.this)  
		                                .setTitle("ע��ʧ��").setMessage("ע��ʧ��")  
		                                .setPositiveButton("ȷ��", null).show();  
		                        
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
