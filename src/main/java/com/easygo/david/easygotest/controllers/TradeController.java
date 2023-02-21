package com.easygo.david.easygotest.controllers;

import com.easygo.david.easygotest.controllers.request.TradeRequest;
import com.easygo.david.easygotest.controllers.responses.ExchangeResponse;
import com.easygo.david.easygotest.services.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/survivors/trade")
@AllArgsConstructor
@Tag(name = "Trade Controller", description = "This Controller contains the endpoint for trade between Survivors")
public class TradeController {

    @Autowired
    private final TradeService tradeService;
    
    @PutMapping("/{user_id}/target/{target_user_id}")
    @Operation(summary = "Make the trade exchange", description = "This endpoint returns the exchange users involved in the trade transaction.")
    public ResponseEntity<ExchangeResponse> tradeExchange(
            @PathVariable("user_id") UUID own_id,
            @PathVariable("target_user_id") UUID target_id,
            @RequestBody TradeRequest tradeRequest) {
        return new ResponseEntity<>(new ExchangeResponse("Trade done!",
                own_id.toString(),
                tradeService.exchange(own_id, target_id, tradeRequest)),
                HttpStatus.OK);
    }

}
