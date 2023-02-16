package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class subscribeResponseDto {

    private BigInteger userId;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState; // mariadb에서는 Integer
    private Integer equalState;
}
