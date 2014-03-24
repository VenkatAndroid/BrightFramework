package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.webkit.WebView;

import com.mobifusion.androidmrv.rb.util.RbUtil;
import com.mobifusion.minerva_lib.activities.InfoMobi;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.dao.Title;
import com.mobifusion.minerva_lib.data.DataHolder;
import com.mobifusion.minerva_lib.util.FileUtil;
import com.mobifusion.minerva_lib.util.MobiLog;
import com.mobifusion.rb.tables.Chapter;
import com.mobifusion.rb.tables.RecordedBook;


@SuppressWarnings("unused")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class InfoActivity extends InfoMobi {

public int getActionBarColorId() {
return R.color.action;
}

protected int getLayoutId() {
return R.layout.info;
}

public int getTitleId() {
return R.string.info;
}

protected int getWebViewId() {
return R.id.web_view_info;
}

protected void loadUrl(WebView webView) {
webView.setBackgroundColor(0x00000000);

ContentPagerActivity.init(webView);
String html = null;
int tblcount = 0;
PageInstance pg = new PageInstance();
Cursor c = pg.getInfotblName();
if (c.moveToFirst()) {

tblcount = c.getInt(0);
MobiLog.l(this, "isRecordeTable::::::::::" + c.getInt(0));

}
if (tblcount == 1) {
	
	
	MobiLog.l(this, "TableCOUNT::::::::::" + c.getInt(0));
RecordedBook recordedBook = new RecordedBook();
recordedBook.getFromDb();

Chapter chapterDb = new Chapter();
List<Chapter> chapters = chapterDb.findAll();

 MobiLog.l(this,"chapters.size()=" + chapters.size());

String totalTime = processTime(chapters);

html = FileUtil.readFromFile("html/intro.html", this);

html = html
.replace("rb__title", RbUtil.fix(recordedBook.getName()));

if (RbUtil.fix(recordedBook.getAuthors()).length() == 0) {
html = html.replace("<span class=\"name\">Author</span>", "");
}
if (RbUtil.fix(recordedBook.getNarrators()).length() == 0) {
html = html.replace("<span class=\"name\">Narrator</span>", "");
}
if (recordedBook.getPublisherName().length() == 0) {
html = html
.replace("<span class=\"name\">Publisher</span>", "");
}
if (recordedBook.getDescription().length() == 0) {
html = html.replace("<span class=\"name\">Playing time</span>",
"");
}

html = html.replace("rb__author",
RbUtil.fix(recordedBook.getAuthors()));


html = html.replace("rb__narrators",
RbUtil.fix(recordedBook.getNarrators()));


html = html.replace("rb__publisherName",
recordedBook.getPublisherName());



html = html.replace("rb__publisherName",
recordedBook.getPublisherName());



html = html.replace("rb__description",
recordedBook.getDescription());




html = html.replace("rb__length", totalTime);





} else {
html = FileUtil.readFromFile("html/info.html", this);

Title title = DataHolder.getTitle();

html = html.replace("mobi__title", title.getName());
if (title.getAuthor().length() == 0) {
html = html.replace("<span class=\"name\">Author</span>", "");
html = html
.replace("<span class=\"name\">Publisher</span>", "");
}
if (title.getPublisher().length() < 1) {
html = html.replace("mobi__publisherName:", "");
html = html.replace("Publisher:", "");


}
html = html.replace("mobi__author", title.getAuthor());



html = html.replace("mobi__publisherName", title.getPublisher());


}
webView.loadDataWithBaseURL("file:///android_asset/", html,
"text/html", "UTF-8", null);


}


private String convertSeconds(int secondsIn) {

long hours = TimeUnit.SECONDS.toHours(secondsIn);
long minutes = TimeUnit.SECONDS.toMinutes(secondsIn)
- (TimeUnit.SECONDS.toHours(secondsIn) * 60);
long seconds = TimeUnit.SECONDS.toSeconds(secondsIn)
- (TimeUnit.SECONDS.toMinutes(secondsIn) * 60);

StringBuilder builder = new StringBuilder();

builder.append(hours);
builder.append(" ");

builder.append(getString(com.mobifusion.minerva_lib.R.string.hours));

if (minutes > 0) {
builder.append(", ");
builder.append(minutes);
builder.append(" ");
builder.append(getString(com.mobifusion.minerva_lib.R.string.minutes));
}

return builder.toString();

}

private int computeSeconds(String timestampStr) {
	
	String time=timestampStr.replace(".",":");
	String app="00"+":"+time;
	
String[] tokens = app.split(":");
int hours = Integer.parseInt(tokens[0]);
int minutes = Integer.parseInt(tokens[1]);
int seconds = Integer.parseInt(tokens[2]);
return (3600 * hours) + (60 * minutes) + seconds;

}


private int computeSeconds2(String timestampStr) {
	
String[] tokens = timestampStr.split(":");
int hours = Integer.parseInt(tokens[0]);
int minutes = Integer.parseInt(tokens[1]);
int seconds = Integer.parseInt(tokens[2]);
return (3600 * hours) + (60 * minutes) + seconds;
}

private String processTime(List<Chapter> chapters) {

int duration = 0;

for (Chapter chapter : chapters) {
String timeString = chapter.getLength();
if(timeString.contains(":"))
{

duration += computeSeconds2(timeString);
}
else
{
duration += computeSeconds(timeString);
}


}

return convertSeconds(duration);

}


}
