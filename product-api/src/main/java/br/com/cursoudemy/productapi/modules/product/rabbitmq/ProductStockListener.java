package br.com.cursoudemy.productapi.modules.product.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.modules.product.model.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;


@Component
public class ProductStockListener {
    private static final Logger logger = Logger.getLogger(ProductStockListener.class.getName());

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void recieveProductStockMessage(ProductStockDTO productStockDTO) throws JsonProcessingException {
        logger.info("Recebendo mensagem: {}" + new ObjectMapper().writeValueAsString(productStockDTO));
        productService.updateProductStock(productStockDTO);
    }

}
