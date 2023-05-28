package com.example.EStore.Controller;

import com.example.EStore.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Dto.ResponseDto.OrderResponseDto;
import com.example.EStore.Model.Item;
import com.example.EStore.Service.CartService;
import com.example.EStore.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add-cart")
    public ResponseEntity<Object> addCard(@RequestBody ItemRequestDto itemRequestDto){
        try{
            /*Create the item*/
            Item item = itemService.createItem(itemRequestDto);

            /*Add created item to cart*/
            CartResponseDto cartResponseDto = cartService.addToCart(item, itemRequestDto);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*Check out the item from the cart*/
    @PostMapping("/checkout-cart")
    public ResponseEntity<Object> checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutCartRequestDto);
            return new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
