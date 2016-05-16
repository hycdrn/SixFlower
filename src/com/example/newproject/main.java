package com.example.newproject;
  

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
 
public class main extends Activity {  

     
	private View view1, view2, view3,view4,view5;  
    private ViewPager viewPager;  //对应的viewPager        
    private List<View> viewList;//view数组  
    private RadioButton comic,novie,my,animation,search;
    @Override 
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        //设置当前的Activity的界面布局  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second); 
        
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        
        
        
        comic=(RadioButton)findViewById(R.id.comic);
        animation=(RadioButton)findViewById(R.id.animation);
        novie=(RadioButton)findViewById(R.id.novel);
        my=(RadioButton)findViewById(R.id.my);
        search=(RadioButton)findViewById(R.id.searcher);
        
        
        LayoutInflater inflater=getLayoutInflater();  
        view1 = inflater.inflate(R.layout.second_1, null);  
        view2 = inflater.inflate(R.layout.second_2,null);  
        view3 = inflater.inflate(R.layout.second_3, null); 
        view4 = inflater.inflate(R.layout.second_4, null);
        view5 = inflater.inflate(R.layout.second_5, null);
          
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中  
        viewList.add(view1);  
        viewList.add(view2);  
        viewList.add(view3);  
        viewList.add(view4);
        viewList.add(view5);
        PagerAdapter pagerAdapter = new PagerAdapter() {  
            
            @Override  
            public boolean isViewFromObject(View arg0, Object arg1) {  
                // TODO Auto-generated method stub  
                return arg0 == arg1;  
            }  
              
            @Override  
            public int getCount() {  
                // TODO Auto-generated method stub  
                return viewList.size();  
            }  
              
            @Override  
            public void destroyItem(ViewGroup container, int position,  
                    Object object) {  
                // TODO Auto-generated method stub  
                container.removeView(viewList.get(position));  
            }  
              
            @Override  
            public Object instantiateItem(ViewGroup container, int position) {  
                // TODO Auto-generated method stub  
                container.addView(viewList.get(position));  
                  
                  
                return viewList.get(position);  
            }  
        };  
        viewPager.setAdapter(pagerAdapter); 
        viewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int id) {
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int id) {
				switch(id){
				case 0:
					comic.setChecked(true);
					comic.setText("1");
					animation.setText("动画");
					novie.setText("小说");
					my.setText("我的");
					search.setText("搜索");
					break;
				case 1:
					animation.setChecked(true);
					comic.setText("漫画");
					animation.setText("2");
					novie.setText("小说");
					my.setText("我的");
					search.setText("搜索");
					break;
				case 2:
					novie.setChecked(true);
					comic.setText("漫画");
					animation.setText("动画");
					novie.setText("3");
					my.setText("我的");
					search.setText("搜索");
					break;
				case 3:
					my.setChecked(true);
					comic.setText("漫画");
					animation.setText("动画");
					novie.setText("小说");
					my.setText("4");
					search.setText("搜索");
					break;
				case 4:
					search.setChecked(true);
					comic.setText("漫画");
					animation.setText("动画");
					novie.setText("小说");
					my.setText("我的");
					search.setText("5");
					break;
				default: 
					break;
				
				}
				
				
			}
        	
        });
       
    }  
} 