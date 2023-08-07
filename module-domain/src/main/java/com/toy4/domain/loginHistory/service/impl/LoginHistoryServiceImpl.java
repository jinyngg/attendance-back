package com.toy4.domain.loginHistory.service.impl;

import com.toy4.domain.loginHistory.domain.LoginHistory;
import com.toy4.domain.loginHistory.dto.LoginHistoryDto;
import com.toy4.domain.loginHistory.repository.LoginHistoryRepository;
import com.toy4.domain.loginHistory.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public void saveLoginHistory(LoginHistoryDto loginHistoryDto) {
        LoginHistory loginHistory = LoginHistory.fromDto(loginHistoryDto);
        loginHistoryRepository.save(loginHistory);
    }

}
