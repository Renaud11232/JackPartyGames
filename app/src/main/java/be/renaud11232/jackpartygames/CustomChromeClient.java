package be.renaud11232.jackpartygames;

import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;

public class CustomChromeClient extends WebChromeClient {

    private MainActivity activity;

    public CustomChromeClient(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPermissionRequest(PermissionRequest request) {
        activity.requestPermissions(request);
    }
}
