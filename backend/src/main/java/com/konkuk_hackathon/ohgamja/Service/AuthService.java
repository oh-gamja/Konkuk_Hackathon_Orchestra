package com.konkuk_hackathon.ohgamja.Service;

import com.konkuk_hackathon.ohgamja.Auth.JwtTokenProvider;
import com.konkuk_hackathon.ohgamja.Auth.Kakao.KakaoUserProvider;
import com.konkuk_hackathon.ohgamja.Common.Exception.InvalidTokenException;
import com.konkuk_hackathon.ohgamja.Dao.UserDao;
import com.konkuk_hackathon.ohgamja.Domain.User;
import com.konkuk_hackathon.ohgamja.Dto.Request.LoginRequest;
import com.konkuk_hackathon.ohgamja.Dto.Response.KakaoPlatformUserResponse;
import com.konkuk_hackathon.ohgamja.Dto.Response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.konkuk_hackathon.ohgamja.Common.Response.Status.BaseExceptionResponseStatus.TOKEN_MISMATCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;
    private final KakaoUserProvider kakaoUserProvider;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse kakaoLogin(LoginRequest loginRequest) {
        KakaoPlatformUserResponse response = kakaoUserProvider.getKakaoPlatformUser(loginRequest.getIdToken());
        return generateOAuthTokenResponse(response.getName(), response.getEmail(), response.getPlatformId(), response.getImage());
    }

    private LoginResponse generateOAuthTokenResponse(String name, String email, String platformId, String imageUrl) {
        List<Long> userId = userDao.findIdByPlatformId(platformId);
        if (!userId.isEmpty()) {
            User findUser = userDao.findUserById(userId.get(0));

            String token = issueToken(findUser);

            // OAuth 로그인은 성공했지만 회원가입에 실패한 경우
            if (!(findUser.getName() == null)) {
                return new LoginResponse(token, findUser.getEmail(), false);
            } else {
                return new LoginResponse(token, findUser.getEmail(), true);
            }
        } else {
            User oauthUser = new User(name, email, platformId, imageUrl);
            User savedUser = userDao.save(oauthUser);
            String token = issueToken(savedUser);
            return new LoginResponse(token, email, true);
        }
    }

    private String issueToken(final User findUser) {
        return jwtTokenProvider.createToken(findUser.getUserId());
    }

    public Long getUserIdByEmail(String email) {
        try {
            return userDao.findIdByEmail(email);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new InvalidTokenException(TOKEN_MISMATCH);
        }
    }
}



