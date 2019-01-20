package com.matao.hookinstrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.matao.reflection.RefInvoke;

/**
 * Created by matao on 2019/1/20
 */
public class HookedInstrumentation extends Instrumentation {

    private static final String TAG = HookedInstrumentation.class.getSimpleName();

    Instrumentation mBase;

    public HookedInstrumentation(Instrumentation mBase) {
        this.mBase = mBase;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {
        Log.d(TAG, "execStartActivity hooked");

        Class[] paramTypes = {
            Context.class, IBinder.class, IBinder.class, Activity.class,
            Intent.class, int.class, Bundle.class
        };
        Object[] paramValues = {
            who, contextThread, token, target,
            intent, requestCode, options
        };
        return (ActivityResult) RefInvoke.invokeInstanceMethod(mBase, "execStartActivity", paramTypes, paramValues);
    }
}
