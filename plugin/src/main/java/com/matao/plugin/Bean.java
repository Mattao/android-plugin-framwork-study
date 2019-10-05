package com.matao.plugin;

import com.matao.lib.IBean;
import com.matao.lib.ICallback;

/**
 * Created by matao on 2019-10-04
 */
public class Bean implements IBean {

    private String name = "matao";
    private ICallback callback;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void register(ICallback callBack) {
        this.callback = callBack;
        clickButton();
    }

    private void clickButton() {
        callback.sendResult("Hello: " + name);
    }
}
