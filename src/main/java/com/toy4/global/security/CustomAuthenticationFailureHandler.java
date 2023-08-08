package com.toy4.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.type.ErrorCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String loginFailureMessage = objectMapper.writeValueAsString(
                CommonResponse.builder()
                        .success(false)
                        .code(ErrorCode.LOGIN_FAILED.name())
                        .message(exception.getMessage())
                        .build());

        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(loginFailureMessage);

    }

}
