package com.lafin.housekeeper.domain.user.util;

import com.lafin.housekeeper.domain.user.model.TokenInfo;
import com.lafin.housekeeper.shared.library.AES256;

import java.time.LocalDateTime;

public class TokenUtils {

    public static String encodeAccessToken(TokenInfo tokenInfo) throws Exception {
        var builder = new StringBuilder();
        builder.append(tokenInfo.getId());
        builder.append("|");
        builder.append(tokenInfo.getEmail());
        builder.append("|");
        builder.append(tokenInfo.getCreatedAt());

        var aes = new AES256();
        return aes.encrypt(builder.toString());
    }

    public static String encodeRefreshToken(TokenInfo tokenInfo, Long expireMinutes) throws Exception {
        var builder = new StringBuilder();
        builder.append(tokenInfo.getId());
        builder.append("|");
        builder.append(tokenInfo.getEmail());
        builder.append("|");
        builder.append(tokenInfo.getCreatedAt().plusMinutes(expireMinutes));

        var aes = new AES256();
        return aes.encrypt(builder.toString());
    }

    public static TokenInfo decodeAccessToken(String accessToken) throws Exception {
        var aes = new AES256();
        var decryptedToken = aes.decrypt(accessToken);
        var tokens = decryptedToken.split("\\|");

        return TokenInfo.builder()
                .id(Long.parseLong(tokens[0]))
                .email(tokens[1])
                .createdAt(LocalDateTime.parse(tokens[2]))
                .build();
    }

    public static TokenInfo decodeRefreshToken(String refreshToken) throws Exception {
        var aes = new AES256();
        var decryptedToken = aes.decrypt(refreshToken);
        var tokens = decryptedToken.split("\\|");

        return TokenInfo.builder()
                .id(Long.parseLong(tokens[0]))
                .email(tokens[1])
                .createdAt(LocalDateTime.parse(tokens[2]))
                .build();
    }
}
