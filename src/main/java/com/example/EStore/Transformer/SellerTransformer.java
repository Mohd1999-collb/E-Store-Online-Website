package com.example.EStore.Transformer;

import com.example.EStore.Dto.RequestDto.SellerRequestDto;
import com.example.EStore.Dto.ResponseDto.SellerResponseDto;
import com.example.EStore.Model.Seller;

public class SellerTransformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .mobNo(sellerRequestDto.getMobNo())
                .emailId(sellerRequestDto.getEmailId())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
}
