package br.com.cursoudemy.productapi.modules.product.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.modules.product.model.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import java.util.Objects;

@Component
public class ProductStockListener {
    private static final Logger logger = Logger.getLogger(ProductStockListener.class.getName());

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    public ProductStockListener(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO productStockDTO) throws JsonProcessingException {
        logger.info(String.format(
                "Revieving message with data: %s and TransactionID: %s",
                objectMapper.writeValueAsString(productStockDTO),
                productStockDTO.getTransactionid()));
        productService.updateProductStock(productStockDTO);
    }

}
