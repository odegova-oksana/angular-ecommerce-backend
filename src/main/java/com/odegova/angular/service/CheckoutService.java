package com.odegova.angular.service;

import com.odegova.angular.dto.Purchase;
import com.odegova.angular.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
