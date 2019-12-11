package be.renaud11232.jackpartygames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    private static final String PERMISSION = Manifest.permission.CAMERA;
    private static final String[] PERMISSIONS = {PERMISSION};
    private static final String[] RESOURCES = {PermissionRequest.RESOURCE_VIDEO_CAPTURE};


    private PermissionRequest latestRequest;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        webView.setWebChromeClient( new CustomChromeClient(this));
        webView.setWebViewClient(new CustomWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        webView.loadUrl(getString(R.string.url_homepage));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE) {
            boolean allGranted = true;
            for(int grantResult : grantResults) {
                if(grantResult == PackageManager.PERMISSION_DENIED) {
                    allGranted = false;
                    break;
                }
            }
            if(allGranted) {
                latestRequest.grant(RESOURCES);
            } else {
                latestRequest.deny();
            }
        }
    }

    public void requestPermissions(PermissionRequest request) {
        if(Arrays.asList(request.getResources()).contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_DENIED) {
                latestRequest = request;
                requestPermissions(PERMISSIONS, REQUEST_CODE);
            } else {
                request.grant(RESOURCES);
            }
        } else {
            request.deny();
        }
    }
}
