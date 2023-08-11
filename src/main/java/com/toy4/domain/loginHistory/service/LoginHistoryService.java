package com.toy4.domain.loginHistory.service;

import com.toy4.domain.loginHistory.dto.LoginHistoryDto;

public interface LoginHistoryService {

    void saveLoginHistory(LoginHistoryDto loginHistoryDto);

}
