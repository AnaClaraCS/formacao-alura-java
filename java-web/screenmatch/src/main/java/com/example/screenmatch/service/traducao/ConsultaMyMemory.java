package com.example.screenmatch.service.traducao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.screenmatch.service.ConsumoAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsultaMyMemory  {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();

        ConsumoAPI consumo = new ConsumoAPI();

        String texto;
        String langpair;
        try {
            texto = URLEncoder.encode(text, "UTF-8");
            langpair = URLEncoder.encode("en|pt-br", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);
        
        DadosTraducao traducao;
        try {
            traducao = mapper.readValue(json, DadosTraducao.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        //System.out.println("Traducao: " + traducao.dadosResposta().textoTraduzido());
        return traducao.dadosResposta().textoTraduzido();
    }
}
