package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.mobi.bright.minerva.dao.quiz.Answers;
import com.mobi.bright.minerva.dao.quiz.Question;
import com.mobi.bright.minerva.dao.quiz.Quiz;
import com.mobifusion.minerva_lib.R;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;
import com.mobifusion.minerva_lib.activities.SplashMobi;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.dao.Tags;
import com.mobifusion.minerva_lib.data.DataHolder;
import com.mobifusion.minerva_lib.sectionlist.EntryAdapter;
import com.mobifusion.minerva_lib.sectionlist.EntryAdapterQuiz;
import com.mobifusion.minerva_lib.sectionlist.EntryItem;
import com.mobifusion.minerva_lib.sectionlist.Item;
import com.mobifusion.minerva_lib.sectionlist.SectionItem;
import com.mobifusion.minerva_lib.util.ActivityUtil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



@SuppressWarnings("unused")
public class Progress extends SherlockActivity   {

	public static ListView progCustomList;
	public static ArrayList<Item> items;
	LinearLayout linearLayout;
	public static List<PageInstance> pageInstances;
	List<Quiz> Quizinstances;
	List<Question>Questioninstances;
	List<Answers>Answersinstances;
	
	PageInstance pInstance;
	boolean freeToSendToContentPageFlag = true;
	public static List<Tags> tagsList = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progmain);
		
	linearLayout = (LinearLayout) findViewById(R.id.main);
	int pageId=1207;
	tagsList = DataHolder.getTagsListMap().get(pageId);// 204
		
	progCustomList = (ListView) getLayoutInflater().inflate(R.layout.proglist_main, null);
		

	pInstance = new PageInstance();
	items = new ArrayList<Item>();
	
	if(SplashMobi.dbsize.equals("small"))
		
	{
		
for (Tags tag : tagsList) {

		
		
		pageInstances = tag.getPageInstances();
	
		Quizinstances=Quiz.getQuizzes();
		Questioninstances=Question.getQuest();
		Answersinstances=Answers.getAnsw();
		
		//This is for showing tags in the listview
		//items.add(new SectionItem(tag.getTag()));
		
		//this is for showing quiz and listview.
		for (int i = 0; i < pageInstances.size(); i++) {
			items.add(new EntryItem(pageInstances.get(i).getTitle(),Quizinstances.get(i).getName(),
					 pageInstances.get(
							i).getId(),pageInstances.get(i).getImage()));
		}

	}
	}
	EntryAdapterQuiz adapter = new EntryAdapterQuiz(getAssets(),Progress.this, items);
	
	progCustomList.setAdapter(adapter);

	progCustomList.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			
			EntryItem item = (EntryItem) items.get(position);

			doListViewClick(pInstance.getTagId(item.pinstnaceId),
					item.pinstnaceId); // pageInstances.get(position)
			// .getId());
			
		}

	});
	linearLayout.addView(progCustomList);
	Runtime.getRuntime().gc();
	
}
	
	private void doListViewClick(int tagId, int pageInstaceId) {
		synchronized (this) {
			if (freeToSendToContentPageFlag) {
		

				ActivityUtil.onTileClick(this, ContentPagerActivity.class,
						pageInstaceId, tagId, android.R.anim.fade_in,
						R.anim.right_slide_in);
				
				freeToSendToContentPageFlag = false;
				alertSingleChoiceItems();
			}
		}// synchronized
		
		
	}
	
	public void showToast(String customText) {
		Toast.makeText(Progress.this, customText.trim(), Toast.LENGTH_LONG)
				.show();
	}

public void alertSingleChoiceItems(){
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(Progress.this);
	    Log.e("answer list",Answersinstances.get(0).getAnswerText());
	    // Set the dialog title
	    builder.setTitle(Questioninstances.get(0).getQuestionText())
	    
		// specify the list array, the items to be selected by default (null for none),
		// and the listener through which to receive call backs when items are selected
	    // again, R.array.choices were set in the resources res/values/strings.xml
	    
	   
	    .setSingleChoiceItems(R.array.choices, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				showToast("Some actions maybe? Selected index: " + arg1);
			}
	
	    })
	           
		 // Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				
				int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				showToast("selectedPosition: " + selectedPosition);
				
			}
		})
		
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				
			}
		})
		
		.show();
	    
	}
	
	
	
}