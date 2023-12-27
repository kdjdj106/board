package com.example.board.converters;

public interface ProviderUserConverter <T, R>{
    R converter(T t);
}
