import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class HttpStatusChecker {
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String DEFAULT_URL = "https://http.cat/";
    public String getStatusImage(int code) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(DEFAULT_URL + code + ".jpg"))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new Exception("Code (" + code + ") not valid");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DEFAULT_URL + code;
    }
}
