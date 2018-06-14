package atrue.newssearch;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import atrue.newssearch.utils.AppConstants;

/**
 * Created by Jayanth on 15/06/18.
 */

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.search_input_screen);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    MenuItem searchItem = menu.findItem(R.id.menu_search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    searchView.setOnQueryTextListener(this);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(
        new ComponentName(this, SearchResultActivity.class)));
    searchView.setFocusable(true);
    searchView.setIconifiedByDefault(false);
    searchView.setFocusable(true);
    searchView.setIconified(false);
    searchView.requestFocusFromTouch();
    return true;
  }

  @Override
  public boolean onQueryTextSubmit(String s) {
    Bundle bundle = new Bundle();
    Intent intent = new Intent(this, SearchResultActivity.class);
    bundle.putString(AppConstants.SEARCH_TERM, s);
    intent.putExtras(bundle);
    startActivity(intent);
    return false;
  }

  @Override
  public boolean onQueryTextChange(String s) {
    return false;
  }
}
