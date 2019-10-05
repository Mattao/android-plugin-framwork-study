package com.matao.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matao.reflection.RefInvoke;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String apkName = "plugin-debug.apk";//assets目录中apk名称

    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;
    private String dexPath = null;    //apk文件地址
    private File fileRelease = null;  //释放目录
    private DexClassLoader classLoader = null;

    private Button mLoadDexBt;
    private TextView mTextTv;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            Utils.extractAssets(newBase, apkName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        File extractFile = this.getFileStreamPath(apkName);
        dexPath = extractFile.getPath();
        fileRelease = this.getDir("dex", Context.MODE_PRIVATE);

        Log.d("DEMO", "dexPath: " + dexPath);
        Log.d("DEMO", "fileRelease.getAbsolutePath(): " +
            fileRelease.getAbsolutePath());

        classLoader = new DexClassLoader(dexPath, fileRelease.getAbsolutePath(),
            null, getClassLoader());

    }

    private void initView() {
        mLoadDexBt = findViewById(R.id.load_dex_bt);
        mTextTv = findViewById(R.id.text_tv);

        mLoadDexBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class loadedClassBean = null;
                try {
                    loadedClassBean = classLoader.loadClass("com.matao.plugin.Bean");
                    Object beanObj = RefInvoke.createObject(loadedClassBean);

                    String name = (String) RefInvoke.invokeInstanceMethod(beanObj, "getName");

                    mTextTv.setText(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
