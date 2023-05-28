package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Dto.ResponseDto.OrderResponseDto;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Exception.EmptyCartException;
import com.example.EStore.Exception.InsufficientQuantityException;
import com.example.EStore.Exception.InvalidCardException;
import com.example.EStore.Model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}
