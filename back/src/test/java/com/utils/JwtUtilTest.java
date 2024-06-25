package com.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    @Test
    void generateTokentest(){
        String token = JwtUtil.generateToken("test");

    }

    @Test
    void parseTokentest(){

    }

    @Test
    void validateTokentest(){

    }
}