package com.matao.hookinstrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.matao.reflection.RefInvoke;

/**
 * Created by matao on 2019/1/20
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getField(Activity.class, this, "mInstrumentation");
        Instrumentation hookedInstrumentation = new HookedInstrumentation(mInstrumentation);

        RefInvoke.setField(Activity.class, this, "mInstrumentation", hookedInstrumentation);
    }
}
