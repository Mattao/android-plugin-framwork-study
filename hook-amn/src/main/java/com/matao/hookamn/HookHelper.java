package com.matao.hookamn;

import com.matao.reflection.RefInvoke;

import java.lang.reflect.Proxy;

/**
 * Created by matao on 2019/1/20
 */
public class HookHelper {

    public static void hookActivityManager() {
        Object gDefault = RefInvoke.getStaticField("android.app.ActivityManagerNative", "gDefault");
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
