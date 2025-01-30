package jala.university.chatcapstone.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.StringJoiner;

public class RequestHandler {

  private final HttpClient httpClient;

  public RequestHandler() {
    this.httpClient = HttpClient.newBuilder()
        .followRedirects(Redirect.NORMAL)
        .build();
  }

  public String sendRequest(String method, String url, Map<String, String> queryParams,
      String jsonBody) {
    try {
      String fullUrl = buildUrlWithParams(url, queryParams);

      HttpRequest request = null;

      if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
        request = HttpRequest.newBuilder()
            .uri(URI.create(fullUrl))
            .header("Content-Type", "application/json")
            .method(method, BodyPublishers.ofString(jsonBody))
            .build();
      } else if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
        request = HttpRequest.newBuilder()
            .uri(URI.create(fullUrl))
            .method(method, BodyPublishers.noBody())
            .build();
      }

      HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
      return response.body();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String buildUrlWithParams(String baseUrl, Map<String, String> queryParams) {
    if (queryParams == null || queryParams.isEmpty()) {
      return baseUrl;
    }
    StringJoiner joiner = new StringJoiner("&", baseUrl + "?", "");
    queryParams.forEach((key, value) -> joiner.add(key + "=" + value));
    return joiner.toString();
  }

  public String get(String url, Map<String, String> queryParams) {
    return sendRequest("GET", url, queryParams, null);
  }

  public String post(String url, String jsonBody) {
    return sendRequest("POST", url, null, jsonBody);
  }

  public String put(String url, String jsonBody) {
    return sendRequest("PUT", url, null, jsonBody);
  }

  public String delete(String url, Map<String, String> queryParams) {
    return sendRequest("DELETE", url, queryParams, null);
  }
}
