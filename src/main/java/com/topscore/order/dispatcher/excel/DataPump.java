package com.topscore.order.dispatcher.excel;

import java.util.List;

/**
 * 数据泵。
 *
 * @author dengb
 */
public interface DataPump<T> {

    List<T> getData();

    boolean hasNext();
}
