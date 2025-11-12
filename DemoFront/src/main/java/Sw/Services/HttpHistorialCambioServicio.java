/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sw.Services;

/**
 *
 * @author Pipea
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import sw2.Modelo.HistorialCambioServicio;

public class HttpHistorialCambioServicio {

    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String API_URL = "http://localhost:8080/historial";

    public HttpHistorialCambioServicio() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    }

    public CompletableFuture<List<HistorialCambioServicio>> obtenerHistorial() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    String responseBody = response.body();
                    System.out.println("[HTTP GET] URL: " + API_URL);
                    System.out.println("[HTTP GET] Cuerpo recibido: " + responseBody);
                    try {
                        return mapper.readValue(responseBody, new TypeReference<List<HistorialCambioServicio>>() {});
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error parseando respuesta: " + responseBody, e);
                    }
                });
    }
}


