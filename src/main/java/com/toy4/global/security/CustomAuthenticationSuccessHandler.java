package com.toy4.global.security;

import static com.toy4.global.response.type.ErrorCode.INVALID_EMAIL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.loginHistory.dto.LoginHistoryDto;
import com.toy4.domain.loginHistory.service.LoginHistoryService;
import com.toy4.domain.refreshToken.domain.RefreshToken;
import com.toy4.domain.refreshToken.repository.RefreshTokenRepository;
import com.toy4.global.jwt.JwtProvider;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.type.SuccessCode;
import com.toy4.global.token.dto.TokenDto;
import com.toy4.global.utils.RequestUtils;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RefreshTokenRepository refreshTokenRepository;
    private final EmployeeRepository employeeRepository;
    private final LoginHistoryService loginHistoryService;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String email = userDetails.getUsername();
        String userAgent = RequestUtils.getUserAgent(request);
        String clientIp = RequestUtils.getClientIp(request);

        Employee employee = getEmployeeByEmail(email);
        Long employeeId = employee.getId();

        TokenDto token = jwtProvider.generateToken(employee.getEmail(), employee.getRole());
        String refreshToken = token.getRefreshToken();

        RefreshToken currentToken = refreshTokenRepository.findByKey(employeeId).orElse(null);
        if (currentToken != null) {
            currentToken.updateToken(refreshToken);
            refreshTokenRepository.save(currentToken);
        } else {
            refreshTokenRepository.save(
                    RefreshToken.builder()
                            .key(employeeId)
                            .token(refreshToken)
                            .build());
        }

        employee.updateLastLoginAt();
        employeeRepository.save(employee);
        loginHistoryService.saveLoginHistory(
                LoginHistoryDto.builder()
                        .employee(employee)
                        .clientIp(clientIp)
                        .userAgent(userAgent)
                        .build());

        HashMap<String, Object> data = new HashMap<>();
        data.put("id", employeeId);
        data.put("token", token);

        String loginSuccessMessage = objectMapper.writeValueAsString(
                CommonResponse.builder()
                        .success(true)
                        .code(SuccessCode.SUCCESS.name())
                        .message(SuccessCode.SUCCESS.getMessage())
                        .data(data)
                        .build());

        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(loginSuccessMessage);

    }

    private Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeException(INVALID_EMAIL));
    }

}
