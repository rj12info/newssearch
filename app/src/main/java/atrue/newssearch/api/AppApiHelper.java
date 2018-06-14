package atrue.newssearch.api;

import atrue.newssearch.remote.NetworkHelper;
import atrue.newssearch.remote.SearchService;
import atrue.newssearch.viewmodels.SearchViewModel;
import io.reactivex.Single;
import retrofit2.Call;

/**
 * Singleton class to make API calls. We can add more here
 * Created by Jayanth on 15/06/18.
 */

public class AppApiHelper implements ApiHelper {
  private static volatile AppApiHelper INSTANCE;

  private AppApiHelper() {

  }

  public static AppApiHelper getInstance() {
    if (INSTANCE == null) {
      synchronized (AppApiHelper.class) {
        if (INSTANCE == null) {
          INSTANCE = new AppApiHelper();
        }
      }
    }
    return INSTANCE;
  }

  @Override
  public Call<SearchViewModel> getSourceDetails(String queryTerm) {
    return NetworkHelper.getRetrofit()
        .create(SearchService.class).getSourceDetails(queryTerm);
  }
}
