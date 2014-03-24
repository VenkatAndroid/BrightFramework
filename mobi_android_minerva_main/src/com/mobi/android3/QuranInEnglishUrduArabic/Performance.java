package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mobifusion.minerva_lib.R;
import com.mobifusion.minerva_lib.activities.ContentPagerMobi;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.data.DataStorage;
import com.mobifusion.minerva_lib.sql_lite.SqlLiteHelper;
import com.mobifusion.minerva_lib.util.ActivityUtil;





@SuppressWarnings("unused")
public  class Performance extends Activity implements OnClickListener  {
	

	public static List<PageInstance> pageInstances;
	 static PageInstance pInstance;
	 TableLayout scrtable,scrtable2 = null;
	 TableRow scorerow;

	 
	 int pageId;
	
		 int ordno;
		
		 String title,ptitle;
		 int ptagid;
		 int pginsid;
	 TextView even,start;
	 ImageView img;
	 ArrayList<Integer> pginsary=new ArrayList<Integer>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.performain);
		
		RelativeLayout r1=(RelativeLayout)findViewById(R.id.main);

		
		final SqlLiteHelper triviadb = new SqlLiteHelper(
				this,SqlLiteHelper.databaseName);
		///TableRow scorerow = new TableRow(this);
		pInstance = new PageInstance();
		
		scrtable = (TableLayout) findViewById(R.id.cattable3);
		scrtable2 = (TableLayout) findViewById(R.id.cattable4);
		
		Cursor levelscores = triviadb
				.getCats("select Title,OrderNum,PageInstanceId from page_instance p");
		
		if (levelscores != null) {
			if (levelscores.moveToFirst()) {
				do {
					
					String titlename = levelscores.getString(levelscores
							.getColumnIndex("Title"));
					Log.e("titlename",titlename);
				 ordno= levelscores.getInt(levelscores
							.getColumnIndex("OrderNum"));
				 pginsid=levelscores.getInt(levelscores
							.getColumnIndex("PageInstanceId"));
					
					pginsary.add(pginsid);
					
				 even = new TextView(this);
				 start = new TextView(this);
				 img= new ImageView(this);
				 scorerow = new TableRow(this);
			

				//img. setImageDrawable(getResources().getDrawable(R.drawable.list_bg));


Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list_bg);
int width=200;
int height=150;
Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, width, height, true);
img.setImageBitmap(resizedbitmap);
			
					if(ordno % 2 ==1)
					{
						
						//Log.e("odrdno%2","odrdno%2");
						even.setPadding(500,100, 0,0);
						start.setPadding(420,40, 0,0);
						start.setText("Start");
						
						
					}
					else
					{
						even.setPadding(80,120, 0, 0);
						start.setPadding(80,60, 0,0);
						start.setText("Start");
						//Log.e("odrdno%2 else ","odrdno%2 else");
					}
					even.setText(titlename);
					scorerow.addView(even);
				
					even.setId(ordno);
					even.setOnClickListener(this);
					
					
					/*if(ordno%2==0)
					{
						
					
						even.setText(MainMenuMobi.pageInstances.get(i).getTitle());
						even.setTextColor(Color.WHITE);
						even.setPadding(315, 105, 15, 15);
				     
				        //scorerow.addView(even);
						
						even.setTextColor(Color.BLACK);
						even.setPadding(10,60, 0, 0);
						even.setText(titlename);
						even.setId(ordno);
						Log.e("ordno",even.getId()+"");
						even.setOnClickListener(this);
					even.setOnClickListener(new View.OnClickListener() {

				             @Override
				             public void onClick(View v) {
				                 Toast.makeText(getApplicationContext(), "CLICKED even"+v.getId(),
				                         Toast.LENGTH_LONG).show();
				                 
				                 int odda=v.getId();
				                 
				             	if (MainMenuMobi.freeToSendToContentPageFlag) {
				             		

				        			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
				        					PageInstance.getPintanseId(ptitle,odda), ptagid, android.R.anim.fade_in,
				        					R.anim.right_slide_in);

				        			MainMenuMobi.freeToSendToContentPageFlag = false;
				         
				             	}
				             	}
				         });
						
					
						 
				
						scorerow.addView(even);
					
					
					}
		
				
					else
					{
						odd = new TextView(this);
						Log.e("ordno else",ordno+"");
					//odd.setText(MainMenuMobi.pageInstances.get(i).getTitle());
					odd.setTextColor(Color.BLACK);
					odd.setPadding(300,40, 0,0);
					odd.setText(titlename);
					odd.setId(ordno);
					odd.setOnClickListener(this);
					Log.e("ordno",odd.getId()+"");
					odd.setOnClickListener(new View.OnClickListener() {

			             @Override
			             public void onClick(View v) {
			                 Toast.makeText(getApplicationContext(), "CLICKED odd"+v.getId(),
			                         Toast.LENGTH_LONG).show();
			                 int odda=v.getId();
			                 
			             	if (MainMenuMobi.freeToSendToContentPageFlag) {
			             		

			        			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
			        					PageInstance.getPintanseId(ptitle,odda), ptagid, android.R.anim.fade_in,
			        					R.anim.right_slide_in);

			        			MainMenuMobi.freeToSendToContentPageFlag = false;
			         
			             	}
			             	}
			         });
					
				
					scorerow1.addView(odd);
			
					}*/
					
				
					//r1.addView(even);
					/*if(ordno %2 ==0)
					{*/
					scrtable.addView(scorerow);
					
					//}
					
					
					
				
			
				
				} while (levelscores.moveToNext());
				scrtable.addView(img);
				
			
		}
	}
	}			
	public void onClick(View v) 
	{
		int odda=v.getId();
		int pgninsid=pginsary.get(odda-1);
		//Toast.makeText(getApplicationContext(), "CLICKED odd"+v.getId(),
               // Toast.LENGTH_LONG).show();
     	if (MainMenuMobi.freeToSendToContentPageFlag) 
     	{
     		
			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
					pgninsid, ptagid, android.R.anim.fade_in,
					R.anim.right_slide_in);

			//MainMenuMobi.freeToSendToContentPageFlag = false;
     	}
	}
	
			
		

		
}
	
	
