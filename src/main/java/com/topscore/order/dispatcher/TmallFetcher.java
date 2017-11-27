package com.topscore.order.dispatcher;

import com.topscore.order.dispatcher.domain.OrderMessage;
import com.topscore.order.dispatcher.mock.TmallMock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmallFetcher implements Fetcher {

    @Override
    public List<OrderMessage> fetch() {
        return TmallMock.getOrderMessages();
    }
}
