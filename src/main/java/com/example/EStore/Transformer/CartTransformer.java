package com.example.EStore.Transformer;

import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Dto.ResponseDto.ItemResponseDto;
import com.example.EStore.Model.Cart;
import com.example.EStore.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for (Item item : cart.getItems()) {
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));

        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}
