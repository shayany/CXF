package com.evry.cxfdemo.orders;

import static org.junit.Assert.*;

import com.evry.schema.order.ObjectFactory;
import com.evry.schema.order.OrderInquiryResponseType;
import com.evry.schema.order.OrderInquiryType;
import com.evry.service.orders.Orders;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:.//..//..//..//..//resources/test-beans.xml"})
public class DefaultOrdersEndpointTest {

	private Orders orderService;
	private OrderInquiryType orderInquiryType;

	@Autowired
	private JaxWsProxyFactoryBean testOrdersClient;

	@Before
	public void setUp() throws Exception {
		orderService = testOrdersClient.create(Orders.class);
		ObjectFactory  factory = new ObjectFactory();
		orderInquiryType = factory.createOrderInquiryType();
		orderInquiryType.setAccountId(999);
		orderInquiryType.setOrderQuantity(4);
		orderInquiryType.setEan13(1234567890123L);
		orderInquiryType.setUniqueOrderId(123456);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessOrderPlacement() {
		OrderInquiryResponseType response = orderService.processOrderPlacement(orderInquiryType);
		assertTrue(response.getAccount().getAccountId() == 999);
	}

}
