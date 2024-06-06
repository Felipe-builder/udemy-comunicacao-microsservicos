package br.com.cursoudemy.productapi.config;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.cursoudemy.productapi.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static HttpServletRequest getCurrenRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationException("The current request cloud not be proccessed.");
        }
    }
}
