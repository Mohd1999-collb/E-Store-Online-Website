package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.ProductRequestDto;
import com.example.EStore.Dto.ResponseDto.ProductResponseDto;
import com.example.EStore.Enum.Category;
import com.example.EStore.Exception.SellerNotFoundException;
import com.example.EStore.Model.Product;
import com.example.EStore.Model.Seller;
import com.example.EStore.Repository.ProductRepository;
import com.example.EStore.Repository.SellerRepository;
import com.example.EStore.Service.ProductService;
import com.example.EStore.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller == null){
            throw new SellerNotFoundException("EmailId is not registered.");
        }

        /*Dto --> Product(entity)*/
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);

        /*Update the product list*/
        seller.getProducts().add(product);

        product.setSeller(seller);

        /*Saved into database*/
        Seller savedSeller = sellerRepository.save(seller); /*Saved product and seller both*/

        /*Product(entity) --> Dto*/
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductResponseDto> getProductByCategoryAndPrice(Category category, Integer price) {
        List<Product> products = productRepository.findByCategoryAndPrice(category, price);

        /*Product(entity) --> Dto*/
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product: products) {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));

        }
        return productResponseDtos;
    }
}
