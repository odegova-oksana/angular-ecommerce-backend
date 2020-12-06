package com.odegova.angular.dto;

import com.odegova.angular.entity.Address;
import com.odegova.angular.entity.Customer;
import com.odegova.angular.entity.Order;
import com.odegova.angular.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
