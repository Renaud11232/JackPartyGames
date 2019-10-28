package be.renaud11232.jackpartygames;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new CustomWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if(Build.VERSION.SDK_INT >= 21){
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        webView.loadUrl(getString(R.string.url_homepage));
    }
}
