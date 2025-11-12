package Sw.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpService {

    private final HttpClient client;
    private final ObjectMapper mapper;
    private final String API_URL_BASE = "http://localhost:8080/servicio/";

    public HttpService() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    }

    public <T> CompletableFuture<T> get(String url, Class<T> responseType) {
        return get(url, new TypeReference<T>() {
            @Override
            public Type getType() {
                return responseType;
            }
        });
    }

    public <T> CompletableFuture<T> get(String url, TypeReference<T> responseType) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL_BASE + url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    String responseBody = response.body();
                    System.out.println(" [HTTP GET] URL: " + API_URL_BASE + url);
                    System.out.println(" [HTTP GET] Cuerpo recibido: " + responseBody);
                    try {
                        return mapper.readValue(responseBody, responseType);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error parseando respuesta: " + responseBody, e);
                    }
                });
    }

    public <T> CompletableFuture<T> post(String url, Object requestBody, Class<T> responseType) {
        try {
            String jsonBody = mapper.writeValueAsString(requestBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL_BASE + url))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .header("Content-Type", "application/json")
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
                String responseBody = response.body();
                System.out.println(" [HTTP POST] URL: " + API_URL_BASE + url);
                System.out.println(" [HTTP POST] Cuerpo recibido: " + responseBody);
                try {
                    return mapper.readValue(responseBody, responseType);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error parseando respuesta: " + responseBody, e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error creando request POST", e);
        }
    }

    public <T> CompletableFuture<T> put(String url, Object requestBody, Class<T> responseType) {
        try {
            String jsonBody = mapper.writeValueAsString(requestBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL_BASE + url))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .header("Content-Type", "application/json")
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
                String responseBody = response.body();
                System.out.println(" [HTTP PUT] URL: " + API_URL_BASE + url);
                System.out.println(" [HTTP PUT] Cuerpo recibido: " + responseBody);
                try {
                    return mapper.readValue(responseBody, responseType);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error parseando respuesta: " + responseBody, e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error creando request PUT", e);
        }
    }

public CompletableFuture<Void> delete(String url) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(API_URL_BASE + url))
        .DELETE()
        .build();

    return client.sendAsync(request, HttpResponse.BodyHandlers.discarding())
        .thenApply(response -> {
            System.out.println("[HTTP DELETE] URL: " + API_URL_BASE + url);
            System.out.println("[HTTP DELETE] Código: " + response.statusCode());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return null;
            } else {
                throw new RuntimeException("Error HTTP: " + response.statusCode());
            }
        });
}
}



