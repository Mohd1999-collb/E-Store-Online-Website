package com.example.EStore.Transformer;

import com.example.EStore.Dto.RequestDto.ProductRequestDto;
import com.example.EStore.Dto.ResponseDto.ProductResponseDto;
import com.example.EStore.Enum.ProductStatus;
import com.example.EStore.Model.Product;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
