package atrue.newssearch.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import atrue.newssearch.SearchResultActivity;
import atrue.newssearch.api.AppApiHelper;
import atrue.newssearch.listeners.SearchApiListener;
import atrue.newssearch.models.Hits;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jayanth on 15/06/18.
 */

public class SearchViewModel extends ViewModel {

  List<Hits> hits;
  SearchApiListener listener;

  public void getSourceDetails(String searchTerm, SearchApiListener listener) {
    this.listener = listener;
    AppApiHelper.getInstance().getSourceDetails(searchTerm).enqueue(getRetrofitCallback());
  }

  public Callback<SearchViewModel> getRetrofitCallback() {
    listener.isLoading(true);
    return new Callback<SearchViewModel>() {
      @Override
      public void onResponse(Call<SearchViewModel> call, Response<SearchViewModel> response) {
        listener.isLoading(false);
        listener.onSucces((ArrayList<Hits>) response.body().hits);
      }

      @Override
      public void onFailure(Call<SearchViewModel> call, Throwable t) {
        listener.isLoading(false);
        listener.onFailure(t);
      }
    };
  }
}
