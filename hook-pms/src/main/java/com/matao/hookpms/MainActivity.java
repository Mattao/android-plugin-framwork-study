package com.matao.hookpms;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        HookHelper.hookPackageManager(newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_hook_tv)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPackageManager().getInstalledApplications(0);
                }
            });
    }
}