package com.example.EStore.Controller;

import com.example.EStore.Dto.RequestDto.ProductRequestDto;
import com.example.EStore.Dto.ResponseDto.ProductResponseDto;
import com.example.EStore.Enum.Category;
import com.example.EStore.Exception.SellerNotFoundException;
import com.example.EStore.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        }catch (SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-product/category/{category}/price/{price}")
    public ResponseEntity<List<ProductResponseDto>> getProductByCategoryAndPrice(@PathVariable("category") Category category, @PathVariable("price") Integer price){
        List<ProductResponseDto> productResponseDto = productService.getProductByCategoryAndPrice(category, price);
        return new ResponseEntity<>(productResponseDto, HttpStatus.FOUND);
    }

    // get all the products of a category

    // get all the products in a category who have price greater than 500

    // get the top 5 cheapest products in a category

    // get top 5 costliest products in a category

    // get all the products of seller based on emailid

    // get all the out of stock products for a particular catgeory

    // send an email to the seller of the product if the product is out os stock.
}
