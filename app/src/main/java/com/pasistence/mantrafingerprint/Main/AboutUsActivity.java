package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import org.w3c.dom.Text;

public class AboutUsActivity extends AppCompatActivity {
    Context mContext;
    TextView txtAboutUs;
    WebView webAbout = null;
    //private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mContext = AboutUsActivity.this;
        txtAboutUs = (TextView) findViewById(R.id.txt_aboutus);
       this.webAbout = (WebView) findViewById(R.id.webAboutUs);


        /*WebSettings webSettings = webAbout.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webAbout.loadUrl("https://www.journaldev.com");*/


       // this.webAbout = (WebView) findViewById(R.id.webAboutUs);

        WebSettings webSettings = webAbout.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webAbout.setWebViewClient(webViewClient);

        webAbout.loadUrl("http://www.pasistence.com/portfolio.html ");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webAbout.canGoBack()) {
            this.webAbout.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
