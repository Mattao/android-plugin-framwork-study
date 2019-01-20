package com.matao.hookhcallback;

import android.os.Handler;

import com.matao.reflection.RefInvoke;

/**
 * Created by matao on 2019/1/20
 */
public class HookHelper {

    public static void attachBaseContext() {
        Object currentActivityThread = RefInvoke.getStaticField("android.app.ActivityThread", "sCurrentActivityThread");
        Handler mH = (Handler) RefInvoke.getField("android.app.ActivityThread", currentActivityThread, "mH");

        RefInvoke.setField(Handler.class, mH, "mCallback", new HookedCallback(mH));
    }
}
