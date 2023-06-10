package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.OrderRequestDto;
import com.example.EStore.Dto.ResponseDto.OrderResponseDto;
import com.example.EStore.Exception.*;
import com.example.EStore.Model.Card;
import com.example.EStore.Model.Cart;
import com.example.EStore.Model.OrderEntity;

import java.util.List;

public interface OrderService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidQuantityException, InvalidCardException;

    public OrderEntity placeOrder(Card card, Cart cart) throws InsufficientQuantityException;

    List<Integer> top5OrdersWithHighestOrderValue();

    List<Integer> allOrderOfParticularCustomer(String emailId);

    List<Integer> top5RecentlyOrders();
}
