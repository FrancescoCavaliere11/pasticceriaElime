package org.elime.elimebackend.data.dto.response;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private String userId;
}
