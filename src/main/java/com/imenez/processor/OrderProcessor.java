package com.imenez.processor;

import com.imenez.dto.Order;
import com.imenez.dto.OrderResponse;
import com.imenez.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements Processor {

    @Autowired
    private OrderService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        OrderResponse response = new OrderResponse();

        service.addOrder(exchange.getIn().getBody(Order.class));
        response.setMessage("Order added: " + exchange.getIn().getBody(Order.class).getName());

        exchange.getOut().setBody(response);

    }
}
