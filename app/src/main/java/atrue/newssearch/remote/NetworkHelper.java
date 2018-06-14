package atrue.newssearch.remote;

import java.util.concurrent.TimeUnit;


import atrue.newssearch.utils.AppConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jayanth on 15/06/18.
 */

public class NetworkHelper {

  public static Retrofit getRetrofit() {
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
        .client(getOkHTTPClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .addConverterFactory(GsonConverterFactory.create());
    return builder.build();
  }

  private static OkHttpClient getOkHTTPClient() {
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().addInterceptor(getLoggingInterceptor())
        .connectTimeout(AppConstants.NETWORK_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout
            (AppConstants.NETWORK_TIME_OUT, TimeUnit.MILLISECONDS);
    return okHttpClient.build();
  }

  private static HttpLoggingInterceptor getLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    return loggingInterceptor;
  }
}
