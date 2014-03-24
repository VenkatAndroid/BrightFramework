package com.mobi.android3.QuranInEnglishUrduArabic;





import  com.mobi.android3.QuranInEnglishUrduArabic.R;

import com.actionbarsherlock.widget.SearchView;
import com.mobifusion.androidmrv.minerva_client.menu.MainMenuActionProvider;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;

public class MainMenu extends MainMenuMobi {


public int getBackgroundId() {
return R.drawable.list_bg;
}
public int getBackgroundIdp() {
return R.drawable.list_bgprogress;
}

public int getSearchListViewBackgroundColorId() {
return R.color.white;
}

public int getActionBarColorId() {
return R.color.action;
}

public Class getContentClass() {
return ContentPagerActivity.class;
}

public int getSearchTextViewId() {
return R.layout.search_text_view__old;
}

public SearchView createMenu(
com.actionbarsherlock.view.MenuInflater inflater,
com.actionbarsherlock.view.Menu menu) {

MainMenuActionProvider.setMainMenu(this);

inflater.inflate(R.menu.main_menu, menu);

//com.actionbarsherlock.view.MenuItem searchItem = menu.findItem(R.id.menu_search);

// todo make SQLite fts3 search
// http://www.mysamplecode.com/2011/11/android-searchview-using-sqlite-fts3.html

//SearchView mSearchView = (SearchView) searchItem.getActionView();
//mSearchView.setOnQueryTextListener(this);
// mSearchView.setOnCloseListener(this);
// mSearchView.setOnQueryTextFocusChangeListener((View.OnFocusChangeListener)
// activity);

//mSearchView.setQueryHint(getString(com.mobifusion.minerva_lib.R.string.search_hint));

return null;
}
}

