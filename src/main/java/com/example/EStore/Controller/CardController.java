package com.example.EStore.Controller;

import com.example.EStore.Dto.RequestDto.CardRequestDto;
import com.example.EStore.Dto.ResponseDto.CardResponseDto;
import com.example.EStore.Enum.CardType;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")

public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add-card")
    public ResponseEntity<Object> addCAed(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        }catch(CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /* Tell me the card type which exists max number of times. */
    @GetMapping("/card-type-max-number-of-times")
    public ResponseEntity<String> cardTypeMaxNumberOfTimes(){
        String cardType = cardService.cardTypeMaxNumberOfTimes();
        return new ResponseEntity<>(cardType, HttpStatus.FOUND);
    }

    /* Tell me the card type which exists min number of times. */
    @GetMapping("/card-type-min-number-of-times")
    public ResponseEntity<String> cardTypeMinNumberOfTimes(){
        String cardType = cardService.cardTypeMinNumberOfTimes();
        return new ResponseEntity<>(cardType, HttpStatus.FOUND);
    }
}
