package com.mobi.android3.QuranInEnglishUrduArabic;






import java.util.Date;

import android.util.Log;

import com.mobifusion.minerva_lib.activities.ContentPagerMobi;

public class ContentPagerActivity extends ContentPagerMobi {
	
public int getActionBarColorId() {
return R.color.action;
}

public Class getMainMenuClass() {
return MainMenu.class;

}

/*
*
* protected int getBackGroundResourceId() {
*
* MobiLog.l(this, " R.drawable.splash=" + R.drawable.splash);
*
* return R.drawable.splash; }
*/

protected Class getVideoClass() {
return VideoViewer.class;
}

@SuppressWarnings("rawtypes")
protected Class getContentClass() {
return this.getClass();
}


public void reportPosition(final double currentPercentage, final double maxPercentage) {
	// TODO Auto-generated method stub
	runOnUiThread(new Runnable() {
        @Override
        public void run() {

            Log.e("currentPercentage=" , currentPercentage+"");
       
          
      


            Date now = new Date();

            int maxPercentageInt = (int) maxPercentage;

            if (maxPercentage > 95) {
                maxPercentageInt = 100;

                if (pageInstance.getDateRead() == null) {
                    pageInstance.setDateRead(now.getTime());
                }
            }

            if (pageInstance.getMaxPercentage() == null ||
                maxPercentageInt > pageInstance.getMaxPercentage()) {
                pageInstance.setMaxPercentage(maxPercentageInt);
            }

            pageInstance.setCurrentPercentage((int) currentPercentage);
            pageInstance.setLastUpdate(now.getTime());

            pageInstance.update();


            Log.e("pageInstance.getMaxPercentage()=" , pageInstance.getMaxPercentage()+"");
            Log.e("currrrrrrrrrrpercentage",currentPercentage+"");
        curper=(int)currentPercentage;
      double  curper2=(double)currentPercentage;
        Log.e("curper value",curper+"");
        Log.e("curper double value",curper2+"");  
           /* WebSocket webSocket = WebSocket.getInstance();

            if (webSocket != null) {
                webSocket.createPagePositionEvent(pageInstance,
                                                  (int) currentPercentage,
                                                  pageInstance.getMaxPercentage());
            }*/
}

	});
}
}
