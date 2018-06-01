package com.zhr.test.string;

public interface OnRequestListener<T> {

    void onsuccess(T response);

    void onFail(String errCode , String errMsg);
}
