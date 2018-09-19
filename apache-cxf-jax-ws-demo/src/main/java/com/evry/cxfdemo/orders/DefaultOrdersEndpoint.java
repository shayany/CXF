package com.evry.cxfdemo.orders;

import com.evry.schema.order.AccountType;
import com.evry.schema.order.ObjectFactory;
import com.evry.schema.order.OrderInquiryResponseType;
import com.evry.schema.order.OrderInquiryType;
import com.evry.service.orders.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(
        portName = "OrderSOAP",
        serviceName = "Orders",
        endpointInterface = "com.evry.service.orders.Orders",
        targetNamespace = "http://www.evry.com/service/Orders/"
)
public class DefaultOrdersEndpoint implements Orders {

    @Autowired
    private OrderService orderService;
    @Override
    public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {
        /*ObjectFactory factory = new ObjectFactory();
        OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
        AccountType account = factory.createAccountType();
        account.setAccountId(1);
        response.setAccount(account);
        return response;*/

        OrderInquiryResponseType response = orderService.processOrder(
                orderInquiry.getUniqueOrderId(),
                orderInquiry.getOrderQuantity(),
                orderInquiry.getAccountId(),
                orderInquiry.getEan13()
        );
        return response;
    }
}
