package com.evry.cxfdemo.orders;

import com.evry.schema.order.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DefaultOrderService implements OrderService{
    @Override
    public OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13) {

        ObjectFactory factory = new ObjectFactory();
        OrderInquiryResponseType response = factory.createOrderInquiryResponseType();

        AccountType account = factory.createAccountType();
        account.setAccountId(accountId);
        response.setAccount(account);

        OrderItemType orderItem = factory.createOrderItemType();
        BookType book = factory.createBookType();
        book.setEan13(ean13);
        book.setTitle("A CXF Book");
        orderItem.setBook(book);

        try{
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(new Date(System.currentTimeMillis()));
            XMLGregorianCalendar estimatedShippingDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            orderItem.setExpectedShippingDate(estimatedShippingDate);
            orderItem.setLineNumber(Integer.valueOf(1));
            orderItem.setPrice(new BigDecimal(5));
            orderItem.setQuantity(orderQuantity);
        }catch (Exception e){

        }
        OrderType order = factory.createOrderType();
        order.setOrderStatus(OrderStatusType.READY);
        order.getOrderItems().add(orderItem);
        response.setOrder(order);
        return response;
    }
}
