package com.matao.hookhcallback;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by matao on 2019/1/20
 */
public class HookedCallback implements Handler.Callback {

    private static final String TAG = HookedCallback.class.getSimpleName();
    private Handler mBase;

    public HookedCallback(Handler base) {
        this.mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 100:   // mH 的 LAUNCH_ACTIVITY
                handleLaunchActivity(msg);
                break;
        }
        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        Object o = msg.obj;
        Log.d(TAG, o.toString());
    }
}
