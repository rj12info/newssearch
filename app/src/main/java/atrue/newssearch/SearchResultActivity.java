package atrue.newssearch;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import atrue.newssearch.adapter.SearchResultsAdapter;
import atrue.newssearch.databinding.SearchResultsScreenBinding;
import atrue.newssearch.listeners.SearchApiListener;
import atrue.newssearch.models.Hits;
import atrue.newssearch.ui.base.BaseActivity;
import atrue.newssearch.utils.AppConstants;
import atrue.newssearch.utils.CommonUtils;
import atrue.newssearch.viewmodels.SearchViewModel;

/**
 * Created by Jayanth on 15/06/18.
 */
public class SearchResultActivity extends BaseActivity<SearchViewModel, SearchResultsScreenBinding> {

  private SearchResultsScreenBinding searchResultsDataBinding;
  private SearchViewModel searchViewModel;
  private SearchResultsAdapter searchResultsAdapter;
  private String searchTerm;
  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    searchResultsDataBinding = getDataBinding();
    initData();
  }

  private void initData() {
    Bundle bundle = getIntent().getExtras();
    if (bundle != null ) {
      searchTerm = bundle.getString(AppConstants.SEARCH_TERM);
    }
    if (TextUtils.isEmpty(searchTerm)) {
      finish();
    }
    searchViewModel.getSourceDetails(searchTerm, new SearchApiListener() {
      @Override
      public void onSucces(ArrayList<Hits> hits) {
        initRecyclerView(hits);
      }

      @Override
      public void onFailure(Throwable t) {
        Toast.makeText(SearchResultActivity.this,"Unable to connect to servers. Please try again later", Toast.LENGTH_LONG).show();
      }

      @Override
      public void isLoading(boolean isLoading) {
        if(isLoading) {
          showProgressBar();
        } else {
          hideProgressBar();
        }
      }
    });
  }

  public void showProgressBar() {
    hideProgressBar();
    if (progressDialog == null) {
      progressDialog = CommonUtils.showLoadingDialog(this);
      progressDialog.setCancelable(true);
    }
    progressDialog.show();
  }

  public void hideProgressBar() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }
  @Override
  public SearchViewModel getViewModel() {
    searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
    return searchViewModel;
  }

  private void initRecyclerView(ArrayList<Hits> hits) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    searchResultsDataBinding.rv.setLayoutManager(layoutManager);
    searchResultsAdapter =
        new SearchResultsAdapter(hits);
    searchResultsDataBinding.rv.addItemDecoration(
        new DividerItemDecoration(this, LinearLayout.VERTICAL));
    searchResultsDataBinding.rv.setAdapter(searchResultsAdapter);
  }

  @Override
  public int getDataBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.search_results_screen;
  }
}
