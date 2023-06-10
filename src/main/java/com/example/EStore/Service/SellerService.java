package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.SellerRequestDto;
import com.example.EStore.Dto.ResponseDto.SellerResponseDto;
import com.example.EStore.Exception.SellerNotFoundException;

import java.util.List;
import java.util.Set;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    void updateSellerInfo(String emailId, String name, String mobNo) throws SellerNotFoundException;

    List<String> sellersSellProductsOfAParticularCategory(String category);

    List<String> productSoldBySellerInAParticularCategory(String category);

    List<String> sellerMaxProduct();

    List<String> sellerMinProduct();

    Set<String> sellerCostliestProduct();

    Set<String> sellerCheapestProduct();
}
