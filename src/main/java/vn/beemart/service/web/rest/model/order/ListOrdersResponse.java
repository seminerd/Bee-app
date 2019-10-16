package vn.beemart.service.web.rest.model.order;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;

@JsonRootName("orders")
public class ListOrdersResponse extends ArrayList<OrderResponse> {
    public ListOrdersResponse(Collection<? extends OrderResponse> c) {
        super(c);
    }
}
