package atrue.newssearch.api;

import atrue.newssearch.viewmodels.SearchViewModel;
import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created by Jayanth on 15/06/18.
 */

public interface ApiHelper {
  Call<SearchViewModel> getSourceDetails(String query);
}
