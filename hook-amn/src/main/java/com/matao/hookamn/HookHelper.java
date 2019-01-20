package com.matao.hookamn;

import android.os.Build;

import com.matao.reflection.RefInvoke;

import java.lang.reflect.Proxy;

/**
 * Created by matao on 2019/1/20
 */
public class HookHelper {

    public static void hookActivityManager() {
        Object gDefault;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            gDefault = RefInvoke.getStaticField("android.app.ActivityManagerNative", "gDefault");
        } else {
            gDefault = RefInvoke.getStaticField("anroid.app.ActivityManager", "IActivityManagerSingleton");
        }

        Object rawIActivityManager = RefInvoke.getField("android.util.Singleton", gDefault, "mInstance");

        try {
            Class<?> iActivityManagerInterface = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(iActivityManagerInterface.getClassLoader(),
                new Class[]{iActivityManagerInterface},
                new HookHandler(rawIActivityManager));

            RefInvoke.setField("android.util.Singleton", gDefault, "mInstance", proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
