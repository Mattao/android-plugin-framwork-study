package com.matao.plugin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.matao.lib.IBean;
import com.matao.lib.ICallback;
import com.matao.reflection.RefInvoke;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HostApp";
    private static final String apkName = "plugin-debug.apk";//assets目录中apk名称

    private String dexPath;    //apk文件地址
    private File fileRelease;  //释放目录
    private DexClassLoader classLoader = null;

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

        Log.d(TAG, "dexPath: " + dexPath);
        Log.d(TAG, "fileRelease.getAbsolutePath(): " +
            fileRelease.getAbsolutePath());

        classLoader = new DexClassLoader(dexPath, fileRelease.getAbsolutePath(),
            null, getClassLoader());

    }

    private void initView() {
        mTextTv = findViewById(R.id.text_tv);

        findViewById(R.id.normal_reflection_way_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class loadedClassBean;
                try {
                    loadedClassBean = classLoader.loadClass("com.matao.plugin.Bean");
                    Object beanObj = RefInvoke.createObject(loadedClassBean);

                    String ret = (String) RefInvoke.invokeInstanceMethod(beanObj, "getName");

                    mTextTv.setText("way1: " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.call_way_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class loadedClassBean;
                try {
                    loadedClassBean = classLoader.loadClass("com.matao.plugin.Bean");
                    Object beanObj = RefInvoke.createObject(loadedClassBean);

                    IBean bean = (IBean) beanObj;
                    String ret = bean.getName();

                    mTextTv.setText("way2: " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.call_with_callback_way_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class loadedClassBean;
                try {
                    loadedClassBean = classLoader.loadClass("com.matao.plugin.Bean");
                    Object beanObj = RefInvoke.createObject(loadedClassBean);

                    IBean bean = (IBean) beanObj;
                    ICallback callback = new ICallback() {
                        @Override
                        public void sendResult(String result) {
                            mTextTv.setText("way3: " + result);
                        }
                    };
                    bean.register(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
