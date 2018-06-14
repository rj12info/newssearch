package atrue.newssearch;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import atrue.newssearch.utils.AppConstants;

/**
 * Created by Jayanth on 15/06/18.
 */
public class WebViewActivity extends AppCompatActivity {

  private ProgressBar horizontalPB;
  private String webUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.webviewdetail);
    initData();
    initUI();

  }

  private void initUI() {
    horizontalPB = findViewById(R.id.pb_horizontal);
    WebView webView = findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebChromeClient(new MyWebChromeClient());
    webView.setWebViewClient(new MyWebViewClient());
    webView.loadUrl(webUrl);
  }

  private void initData() {
    Bundle bundle = getIntent().getExtras();
    webUrl = bundle.getString(AppConstants.WEB_URL);
  }

  private class MyWebChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
      super.onProgressChanged(view, newProgress);
      horizontalPB.setProgress(newProgress);
    }
  }

  private class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
      horizontalPB.setProgress(0);
      horizontalPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
      horizontalPB.setProgress(100);
      horizontalPB.setVisibility(View.GONE);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
      super.onReceivedError(view, request, error);
      horizontalPB.setVisibility(View.GONE);
    }

    @SuppressWarnings("deprecation")
    public void onReceivedError(WebView view, int errorCode, String description,
                                String failingUrl) {
      super.onReceivedError(view, errorCode, description, failingUrl);
      horizontalPB.setVisibility(View.GONE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      return false;
    }
  }
}

