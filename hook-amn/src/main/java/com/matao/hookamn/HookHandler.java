package com.matao.hookamn;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by matao on 2019/1/20
 */
public class HookHandler implements InvocationHandler {

    private static final String TAG = HookHandler.class.getSimpleName();

    private Object mBase;

    public HookHandler(Object base) {
        this.mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".endsWith(method.getName())) {
            Log.d(TAG, "startActivity HOOKED!!");
        }
        return method.invoke(mBase, args);
    }
}
