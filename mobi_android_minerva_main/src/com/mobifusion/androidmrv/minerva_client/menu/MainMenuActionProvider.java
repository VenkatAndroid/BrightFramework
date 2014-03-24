package com.mobifusion.androidmrv.minerva_client.menu;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.view.SubMenu;
import com.mobi.android3.QuranInEnglishUrduArabic.ContentPagerActivity;

import com.mobi.android3.QuranInEnglishUrduArabic.MainActivity;
import com.mobi.android3.QuranInEnglishUrduArabic.MainMenu;
import com.mobi.android3.QuranInEnglishUrduArabic.Performance;
import com.mobi.android3.QuranInEnglishUrduArabic.Progress;
import com.mobifusion.minerva_lib.R;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;
import com.mobifusion.minerva_lib.dao.History;
import com.mobifusion.minerva_lib.dao.Page;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.dao.Tags;
import com.mobifusion.minerva_lib.sectionlist.EntryItem;
import com.mobifusion.minerva_lib.sectionlist.SectionItem;
import com.mobifusion.minerva_lib.util.BundleTags;
import com.mobifusion.minerva_lib.util.MobiLog;

public class MainMenuActionProvider extends
com.actionbarsherlock.view.ActionProvider implements
com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener {
// ------------------------------ FIELDS ------------------------------
public static final int PLAY_NOW = 0;
public static final int FAVORITES = 10000;
public static final int RECENT = 20000;
// public static final int SETTINGS = 30000;
public static final int INFO = 40000;
// private static final int MENU_GROUP = 100;
static List<Page> list;
// static int pageId = 1;
Context mContext;
private boolean showHistory = true;
private boolean showInfo = true;
private boolean showFavorites = true;
static MainMenu mainMenu;

static int continueStringId;

public static void setContinueStringId(int continueStringId) {
MainMenuActionProvider.continueStringId = continueStringId;
}

public static void setMainMenu(MainMenu mainMenu) {
MainMenuActionProvider.mainMenu = mainMenu;
}

// -------------------------- STATIC METHODS --------------------------

public static List<Page> getList() {
if (list == null) {
Page page = new Page();
list = page.getPages();
}
Log.e("list","list.");
return list;
}

// public static void setPageId(int pageIdIn) {
// pageId = pageIdIn;
// }

// public static void setPageIdToShowAll() {
// pageId = -1;
// }

// --------------------------- CONSTRUCTORS ---------------------------

public MainMenuActionProvider(Context context) {
super(context);
MobiLog.l(this, "MainMenuActionProvider="
+ context.getClass().getName());

if (context.getClass().getName().contains("HistoryResults")) {
	Log.e("showHistory","showHistory.");
showHistory = false;
}

if (context.getClass().getName().contains("FavoritesResults")) {
showFavorites = false;
Log.e("FavoritesResults","FavoritesResults.");
}

if (context.getClass().getName().contains("InfoActivity")) {
showInfo = false;
Log.e("InfoActivity","InfoActivity.");
}

mContext = context;
}

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface OnMenuItemClickListener
// ---------------------

public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item) {

MobiLog.l(this, item.toString());

if (item.getItemId() == PLAY_NOW) {
	Log.e("hello","hi ssrrassarsasa");
History history = new History();
int id=0;

if (MainMenuMobi.lastPageId==MainMenuMobi.HISTORY_STACK_KEY) {
	Log.e("HISTORY_STACK_KEY","HISTORY_STACK_KEY.");
PageInstance pageInstance = new PageInstance();
List<PageInstance> list = pageInstance.getPagesFromHistory();
if(list.size()>0)
{
	Log.e("HISTORY_STACK_KEY if","HISTORY_STACK_KEY.if");
id = list.get(0).getId();

}


}
else if(MainMenuMobi.lastPageId==MainMenuMobi.FAVORITES_STACK_KEY)
{
	Log.e("HISTORY_STACK_KEY else ","HISTORY_STACK_KEY.else");
	PageInstance pageInstance = new PageInstance();
	List<PageInstance> list = pageInstance.getPagesFromFavorites();
	if(list.size()>0)
	{
		Log.e("HISTORY_STACK_KEY else if","HISTORY_STACK_KEY.else if");
	id = list.get(0).getId();
	
	}
	
}
else 
{
	Log.e("mainMenu.getFirstPageId() ","mainMenu.getFirstPageId()");
id = mainMenu.getFirstPageId();



	}
if(id>0)
{
	Log.e("MainMenuMobi.lastPageId if id>0","MainMenuMobi.lastPageId if id>0");
Intent intent = new Intent();
intent.setClass(mainMenu, ContentPagerActivity.class);

intent.putExtra(BundleTags.PAGE_INSTANCE_ID, id);

mainMenu.startActivity(intent);
}
else
{
	
	Log.e("MainMenuMobi.lastPageId else","MainMenuMobi.lastPageId else");
	MainMenuMobi.lastPageId =1000000005;
	
Intent intent = new Intent();
intent.setClass(mainMenu, ContentPagerActivity.class);

intent.putExtra(BundleTags.PAGE_INSTANCE_ID,MainMenuMobi.lastPageId);

mainMenu.startActivity(intent);


	}
overRide();
return true;
}

if (item.getItemId() < list.size() + 1) {
for (Page page : list) {
if (page.getName().equals(item.getTitle())) {
	
	Log.e("MainMenu","MainMenu");
mainMenu.addToStack();

mainMenu.changePage(page.getId());

}
}
} else {

if (item.getItemId() == (RECENT)) {
	Log.e("RECENT","RECENT");
	Intent intent = new Intent();
	intent.setClass(mContext, Performance.class);
	mContext.startActivity(intent);
	
} else if (item.getItemId() == (FAVORITES)) {
	Log.e("FAVORITES","FAVORITES");
	Intent intent = new Intent();
	intent.setClass(mContext, Progress.class);

	mContext.startActivity(intent);

} else if (item.getItemId() == (INFO)) {
	Log.e("INFO","INFO");
Intent intent = new Intent();
intent.setClass(mContext, MainActivity.class);

mContext.startActivity(intent);

overRide();

} else {
	Log.e("item.getTitle() ","item.getTitle()");
	mainMenu.addToStack();

Toast.makeText(mContext,
item.getTitle() + "-" + item.getItemId(),
Toast.LENGTH_SHORT).show();
}
}

return true;
}


private void overRide() {
Activity activity = (Activity) mContext;

activity.overridePendingTransition(android.R.anim.fade_in,
com.mobifusion.minerva_lib.R.anim.right_slide_out);
}

// -------------------------- OTHER METHODS --------------------------

public boolean hasSubMenu() {
Log.d(this.getClass().getSimpleName(), "hasSubMenu");

return true;
}

public View onCreateActionView() {
Log.d(this.getClass().getSimpleName(), "onPerformDefaultAction");

return null;
}

public void onPrepareSubMenu(SubMenu subMenu) {
Log.d(this.getClass().getSimpleName(), "onPrepareSubMenu");

subMenu.clear();

getList();

subMenu.add(PLAY_NOW, PLAY_NOW, PLAY_NOW, continueStringId)
.setIcon(
com.mobifusion.minerva_lib.R.drawable.ic_go_search_api_holo_light)
.setOnMenuItemClickListener(this);

int id = 1;

for (Page page : list) {

String name;

if (page.getName().equals("mobifusion_data")) {
	
	Log.e("mobifusion_data","mobifusion_data");
name = "Chapters";
} else {
	Log.e("page.getName()","page.getName()");
name = page.getName();
}

subMenu.add(id, id, id, "Performance")
.setIcon(com.mobifusion.minerva_lib.R.drawable.ic_popup_tiles)
.setOnMenuItemClickListener(this);

// if(pageId == page.getId()) {
// subMenu.setGroupEnabled(id,
// false);
// }
id++;
}

subMenu.add(FAVORITES, FAVORITES, FAVORITES,
com.mobifusion.minerva_lib.R.string.favorites)
.setIcon(com.mobifusion.minerva_lib.R.drawable.favorite)
.setOnMenuItemClickListener(this);
Log.e("FAVORITES12","FAVORITES12");
if (!showFavorites) {
subMenu.setGroupEnabled(FAVORITES, false);
}

subMenu.add(RECENT, RECENT, RECENT,
	
com.mobifusion.minerva_lib.R.string.recent)
.setIcon(com.mobifusion.minerva_lib.R.drawable.recent)
.setOnMenuItemClickListener(this);
Log.e("RECENT12","RECENT12");
if (!showHistory) {
subMenu.setGroupEnabled(RECENT, false);
}

/** subMenu.add(SETTINGS, SETTINGS, SETTINGS,
* R.string.settings).setIcon(R.drawable.settings)
* .setOnMenuItemClickListener(this);*/


subMenu.add(INFO, INFO, INFO, com.mobifusion.minerva_lib.R.string.info)
.setIcon(com.mobifusion.minerva_lib.R.drawable.info)
.setOnMenuItemClickListener(this);
Log.e("INFO12","INFO12");
if (!showInfo) {
subMenu.setGroupEnabled(INFO, false);
}
}

}
