package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.SellerRequestDto;
import com.example.EStore.Dto.ResponseDto.SellerResponseDto;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
