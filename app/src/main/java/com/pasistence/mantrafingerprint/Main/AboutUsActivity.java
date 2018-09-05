package com.pasistence.mantrafingerprint.Main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.pasistence.mantrafingerprint.R;

import org.w3c.dom.Text;

public class AboutUsActivity extends AppCompatActivity {
    Context mContext;
    TextView txtAboutUs;
    WebView webAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mInit();
    }
    private void mInit() {
        mContext        = AboutUsActivity.this;
        txtAboutUs      = (TextView) findViewById(R.id.txt_aboutus);
        webAbout        = (WebView)findViewById(R.id.webAboutUs);
    }

}
