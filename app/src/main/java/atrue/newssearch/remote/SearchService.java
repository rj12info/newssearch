package atrue.newssearch.remote;

import atrue.newssearch.viewmodels.SearchViewModel;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jayanth on 15/06/18.
 */

public interface SearchService {
  @GET("search")
  Call<SearchViewModel> getSourceDetails(@Query("query") String queryTerm);
}
