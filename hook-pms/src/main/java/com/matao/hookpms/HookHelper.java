package com.matao.hookpms;

import android.content.Context;
import android.content.pm.PackageManager;

import com.matao.reflection.RefInvoke;

import java.lang.reflect.Proxy;

/**
 * Created by matao on 2019/1/20
 */
public class HookHelper {

    public static void hookPackageManager(Context context) {
        Object currentActivityThread = RefInvoke.getStaticField("android.app.ActivityThread", "currentActivityThread");
        Object sPackageManager = RefInvoke.getField("android.app.ActivityThread", currentActivityThread, "sPackageManager");

        try {
            Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(
                iPackageManagerInterface.getClassLoader(),
                new Class[]{iPackageManagerInterface},
                new HookHandler(sPackageManager));
            RefInvoke.setField(sPackageManager, "sPackageManager", proxy);

            // 替换 ApplicationPackageManager 中的 mPM 对象
            PackageManager pm = context.getPackageManager();
            RefInvoke.setField(pm, "mPM", proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
