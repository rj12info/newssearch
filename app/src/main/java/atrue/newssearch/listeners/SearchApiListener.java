package atrue.newssearch.listeners;

import java.util.ArrayList;

import atrue.newssearch.models.Hits;

/**
 * Created by Jayanth on 15/06/18.
 */
public interface SearchApiListener {
  void onSucces(ArrayList<Hits> hits);
  void onFailure(Throwable t);
  void isLoading(boolean isLoading);
}
