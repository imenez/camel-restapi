package com.imenez.router;

import com.imenez.dto.Order;
import com.imenez.processor.OrderProcessor;
import com.imenez.service.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource  extends RouteBuilder {

    @Autowired
    private OrderService service;

    @BeanInject
    private OrderProcessor processor;



    /* Autowired or constructor inj.
    public ApplicationResource(OrderService service) {
        this.service = service;
    }*/


    @Override
    public void configure() throws Exception {
        //Camel component building
        restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);


        //Get Request
        rest().get("/hellow").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .setBody(constant("hi fella!"));

        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE).type(Order.class).route()
                .setBody(constant(service.getOrders())).endRest();


        /* with supplier lambda
        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .setBody(()->service.getOrders());
                */



        // Post Request

        /*rest().post("/addStaticOrder").produces(MediaType.APPLICATION_JSON_VALUE).route()
                .setBody(constant(service.addOrder(new Order(8,"orderStatic8",800)))).endRest();*/

        rest().post("/addOrder").produces(MediaType.APPLICATION_JSON_VALUE)
                .type(Order.class).outType(Order.class).route().process(processor).endRest();

    }
}
