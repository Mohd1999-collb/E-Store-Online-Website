package com.example.EStore.Transformer;

import com.example.EStore.Dto.ResponseDto.ItemResponseDto;
import com.example.EStore.Dto.ResponseDto.OrderResponseDto;
import com.example.EStore.Model.Customer;
import com.example.EStore.Model.Item;
import com.example.EStore.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity OrderRequestDtoToOrderEntity(Item item, Customer customer){
        return OrderEntity.builder()
            .orderNo(String.valueOf(UUID.randomUUID()))
            .totalValue(item.getRequiredQuantity() * item.getProduct().getPrice())
            .customer(customer)
            .items(new ArrayList<>())
            .build();
    }

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item item : orderEntity.getItems()) {
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .customerName(orderEntity.getCustomer().getName())
                .orderNo(orderEntity.getOrderNo())
                .totalValue(orderEntity.getTotalValue())
                .dateOfOrder(orderEntity.getDateOfOrder())
                .cardUsed(orderEntity.getCardUsed())
                .items(itemResponseDtoList)
                .build();
    }
}
