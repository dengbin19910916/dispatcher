package com.topscore.order.dispatcher.excel;

import java.util.List;

/**
 * 数据泵。
 *
 * @author dengb
 */
public interface DataPump<T> {

    /**
     * 返回数据列表。
     *
     * @return 数据列表
     */
    List<T> getData();

    /**
     * 是否还有下一批数据。
     *
     * @return true - 有，false - 没有。
     */
    boolean hasNext();
}
