package com.hl7.manage;

public interface SqlOpreation<T> {

    void operation(T knife);

    Class getClass(T knife);

}
