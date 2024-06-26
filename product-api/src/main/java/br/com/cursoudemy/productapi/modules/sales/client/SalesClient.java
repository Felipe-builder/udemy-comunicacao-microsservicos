package br.com.cursoudemy.productapi.modules.sales.client;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import br.com.cursoudemy.productapi.modules.sales.dto.SalesProductResponse;

@HttpExchange("/api/order")
public interface SalesClient {

    @GetExchange("/product/{productId}")
    Optional<SalesProductResponse> findSalesByProductId(@PathVariable Long productId,
            @RequestHeader(name = "Authorization") String authorization,
            @RequestHeader(name = "transactionid") String transactionId);

}
