package com.matao.hookamn;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

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
        Log.d(TAG, "You are HOOKED!! method: " + method.getName() + " called with args: " + Arrays.toString(args));
        return method.invoke(mBase, args);
    }
}
