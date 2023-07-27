package com.example.demo.httpServerV2.handler.inter;

import com.example.demo.httpServerV2.entity.MyEntity;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 22:11
 **/
public interface MyHandler {

    /**
     * 初始化handler
     *
     * @param: @param context
     * @return: void
     * @Autor: Han
     */
    public void init(MyEntity context);

    /**
     * handler service(service应该不是这样做的... - -!)
     *
     * @param: @param context
     * @return: void
     * @Autor: Han
     */
    public void service(MyEntity context);

    /**
     * Get形式执行该方法
     *
     * @param: @param context
     * @return: void
     * @Autor: Han
     */
    public void doGet(MyEntity context);

    /**
     * POST形式执行该方法
     *
     * @param: @param context
     * @return: void
     * @Autor: Han
     */
    public void doPost(MyEntity context);

    /**
     * 销毁Handler(并没有销毁... - -!)
     *
     * @param: @param context
     * @return: void
     * @Autor: Han
     */
    public void destory(MyEntity context);
}
