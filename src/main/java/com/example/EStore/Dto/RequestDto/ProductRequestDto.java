package com.example.EStore.Dto.RequestDto;

import com.example.EStore.Enum.Category;
import com.example.EStore.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String sellerEmailId;

    String name;

    Integer price;

    Integer quantity;

    Category category;
}
