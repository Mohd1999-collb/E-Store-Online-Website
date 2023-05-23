package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.SellerRequestDto;
import com.example.EStore.Dto.ResponseDto.SellerResponseDto;
import com.example.EStore.Model.Seller;
import com.example.EStore.Repository.SellerRepository;
import com.example.EStore.Service.SellerService;
import com.example.EStore.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        /*Dto --> Seller(entity)*/
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        /*Saved in Database*/

        Seller savedSeller = sellerRepository.save(seller);

        /*Seller(entity) --> Dto*/
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }

}
