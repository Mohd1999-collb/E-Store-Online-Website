package com.example.EStore.Dto.ResponseDto;

import com.example.EStore.Enum.Category;
import com.example.EStore.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;

    String sellerName;

    Category category;

    Integer price;

    ProductStatus productStatus;
}
