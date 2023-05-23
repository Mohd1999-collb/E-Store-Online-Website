package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.ProductRequestDto;
import com.example.EStore.Dto.ResponseDto.ProductResponseDto;
import com.example.EStore.Enum.Category;
import com.example.EStore.Exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getProductByCategoryAndPrice(Category category, Integer price);
}
