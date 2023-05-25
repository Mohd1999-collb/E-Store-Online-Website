package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Exception.InsufficientQuantityException;
import com.example.EStore.Exception.OutOfStockException;
import com.example.EStore.Exception.ProductNotFoundException;
import com.example.EStore.Model.Item;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}
