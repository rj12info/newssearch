package atrue.newssearch.handler;

import android.view.View;

import atrue.newssearch.models.Hits;
import atrue.newssearch.utils.Navigator;

/**
 * Created by Jayanth on 15/06/18.
 */

public class SourceActionHandler {

  public void showSourceInfo(View view, Hits source) {
    Navigator.launchSourceDetail(view.getContext(), source.getUrl());
  }
}
