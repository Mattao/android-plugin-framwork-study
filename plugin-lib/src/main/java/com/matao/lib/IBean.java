package com.matao.lib;

/**
 * Created by matao on 2019-10-05
 */
public interface IBean {
    String getName();

    void setName(String name);

    void register(ICallback callBack);
}
