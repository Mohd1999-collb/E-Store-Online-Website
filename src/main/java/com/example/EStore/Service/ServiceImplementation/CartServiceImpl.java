package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Dto.ResponseDto.OrderResponseDto;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Exception.EmptyCartException;
import com.example.EStore.Exception.InsufficientQuantityException;
import com.example.EStore.Exception.InvalidCardException;
import com.example.EStore.Model.*;
import com.example.EStore.Repository.*;
import com.example.EStore.Service.CartService;
import com.example.EStore.Service.OrderService;
import com.example.EStore.Transformer.CartTransformer;
import com.example.EStore.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository  cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();

        /*Set the total price of cart*/
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item); // Update the item list
        item.setCart(cart);
        item.setProduct(product);

        /*Saved in database*/
        Cart savedCart  = cartRepository.save(cart);
        Item savedItem = cart.getItems().get(cart.getItems().size() - 1);
        product.getItems().add(savedItem); // Update the item list

        /*SavedCart(Entity) --> DTO*/
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {
        /*Check customer exist or not*/
        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getCustomerEmailId());
        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist.");
        }

        /*Check customer card exist or not*/
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date currDate = new Date();
        if (card == null || card.getCvv() != checkOutCartRequestDto.getCvv() || currDate.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! You can not use this card.");
        }

        /*If cart is empty then throw the exception*/
        Cart cart = customer.getCart();
        if (cart.getItems().size() == 0){
            throw new EmptyCartException("Cart is empty!");
        }

        /*Prepare the order entity*/
        try {
            OrderEntity order = orderService.placeOrder(card, cart);
            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrderEntity().add(savedOrder); // Update the order list
            resetCart(cart);
            return OrderTransformer.OrderToOrderResponseDto(savedOrder);
        }catch (InsufficientQuantityException e){
            throw e;
        }
    }

    private void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for (Item item : cart.getItems()) {
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }
}
