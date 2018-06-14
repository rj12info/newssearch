package atrue.newssearch.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import atrue.newssearch.SearchResultActivity;
import atrue.newssearch.WebViewActivity;

/**
 * * Created by Jayanth on 15/06/18.
 */

public class Navigator {
  private Navigator() {

  }

  public static void launchSourceDetail(Context context, String sourceId) {
    if (sourceId == null || sourceId.length() == 0) {
      Toast.makeText(context,
          "Unable to open url. Please try later", Toast.LENGTH_LONG).show();
      return;
    }
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.putExtra(AppConstants.WEB_URL, sourceId);
    context.getApplicationContext().startActivity(intent);
  }
}
