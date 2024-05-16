package br.com.cursoudemy.productapi.modules.sales.rabbitmq;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.modules.product.rabbitmq.ProductStockListener;
import br.com.cursoudemy.productapi.modules.sales.dto.SalesConfirmationDTO;

@Component
public class SalesConfirmationSender {
    
    private static final Logger logger = Logger.getLogger(ProductStockListener.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

        
    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;
 
    @Value("${app-config.rabbit.routingKey.sales-confirmation}")
    private String salesConfirmationKey;

    public void sendSalesConfirmationMessage(SalesConfirmationDTO salesConfirmationDTO) {
        try {
            logger.info("Sending message: {}\n" + new ObjectMapper().writeValueAsString(salesConfirmationDTO));
            rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationKey, salesConfirmationDTO);
            logger.info("Message was sent successfully!");
        } catch (Exception e) {
            logger.severe("Error while trying to send sales confirmation message: " + e);
        }
    }


}
