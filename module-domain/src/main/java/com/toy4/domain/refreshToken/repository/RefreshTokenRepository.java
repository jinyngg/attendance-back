package com.toy4.domain.refreshToken.repository;


import com.toy4.domain.refreshToken.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // 유저 ID를 통해 토큰 검색
    Optional<RefreshToken> findByKey(Long key);
}
