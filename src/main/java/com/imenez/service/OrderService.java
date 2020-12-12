package com.imenez.service;

import com.imenez.dto.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private List<Order> list = new ArrayList<>();

    @PostConstruct
    public void initDB(){

        list.add(new Order(1,"order1",100));
        list.add(new Order(2,"order2",200));
        list.add(new Order(3,"order3",300));
        list.add(new Order(4,"order4",400));

    }

    public Order addOrder(Order order){

        list.add(order);

        return order;
    }

    public List<Order> getOrders(){


        return list;
    }
}
