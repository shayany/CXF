package com.evry.cxfdemo.orders;

import com.evry.schema.order.OrderInquiryResponseType;

public interface OrderService {
    OrderInquiryResponseType processOrder(int uniqueOrderID,
                                          int orderQuantity,
                                          int accountId,
                                          long ean13);
}
