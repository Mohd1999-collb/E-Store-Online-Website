package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Model.Item;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);
}
