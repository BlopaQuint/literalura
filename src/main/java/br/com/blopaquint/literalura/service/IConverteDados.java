package br.com.blopaquint.literalura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}