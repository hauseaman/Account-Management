package com.vti.testing.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(
                key,
                null,
                "An error occur",
                new Locale("en", "EN")
        );
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = getMessage("AuthenticationEntryPointException.message");
        String detailMessage = authException.getLocalizedMessage();
        int code = 8;
        String moreInformation = "http://localhost:8080/api/v1/exception/8";

        ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(errorResponse);

        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String message = getMessage("AccessDeniedException.message");
        String detailMessage = accessDeniedException.getLocalizedMessage();
        int code = 9;
        String moreInformation = "http://localhost:8080/api/v1/exception/9";

        ErrorResponse errorResponse = new ErrorResponse(message, detailMessage, null, code, moreInformation);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(errorResponse);

        response.setContentType("application/json");
        response.getWriter().println(json);
    }
}
