package com.mobi.android3.QuranInEnglishUrduArabic;

import android.graphics.Color;
import android.os.Bundle;

import com.mobifusion.androidmrv.minerva_client.menu.MainMenuActionProvider;
import com.mobifusion.minerva_lib.activities.SplashMobi;
import com.mobifusion.minerva_lib.util.AndroidUtil;

public class Splash extends SplashMobi {

// -------------------------- OTHER METHODS --------------------------

public int getBackgroundId() {
return R.drawable.splash;
}

public Class getContentClass() {
return ContentPagerActivity.class;
}

protected String getDatabaseName() {
/*
* String filename; final AssetManager assetManager = getAssets(); try {
* // for assets folder add empty string String[] filelist =
* assetManager.list("");
*
* if (filelist == null) { // dir does not exist or is not a directory }
* else { for (int i = 0; i < filelist.length; i++) { // Get filename of
* file or directory filename = filelist[i]; if
* (filename.endsWith(".db")) { // MobiLog.l(this,
* "Databse name from assets " + // filename);
*
* }
*
* } }
*
* // if(filelistInSubfolder == null) ............
*
* } catch (IOException e) { e.printStackTrace(); }
*/
return "thoth.db";

}

public Class getNextClass() {
return MainMenu.class;
}

public int getTilesId() {
return R.drawable.tile;
}

public void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
AndroidUtil.introTextColor = Color.GRAY;
AndroidUtil.introShadowColor = Color.WHITE;
AndroidUtil.titleTextColor = Color.BLACK;
AndroidUtil.titleShadowColor = Color.WHITE;
MainMenuActionProvider
.setContinueStringId(com.mobifusion.minerva_lib.R.string.continue_string);
}
}
