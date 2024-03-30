package com.example.service;

import com.example.entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);

}
