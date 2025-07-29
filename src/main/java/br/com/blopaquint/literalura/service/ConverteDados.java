package br.com.blopaquint.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode results = root.path("results");

            if (results.isArray() && results.size() > 0) {
                JsonNode subnode = results.get(0);
                return mapper.treeToValue(subnode, classe);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}