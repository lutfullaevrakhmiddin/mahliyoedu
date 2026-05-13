package uz.mahliyoedu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class ImageKitService {

    @Value("${IMAGEKIT_PRIVATE_KEY}")
    private String privateKey;

    @Value("${IMAGEKIT_URL_ENDPOINT}")
    private String urlEndpoint;

    // Rasmni ImageKit ga yuklaydi va URL qaytaradi
    public String uploadImage(MultipartFile file, String fileName) {
        try {
            // Faylni Base64 ga aylantirish
            byte[] bytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            // Basic Auth uchun — privateKey + ":" ni Base64 ga aylantirish
            String auth = Base64.getEncoder()
                .encodeToString((privateKey + ":").getBytes());

            // JSON body
            String body = String.format(
                "{\"file\":\"%s\",\"fileName\":\"%s\",\"folder\":\"/mahliyoedu/\"}",
                base64, fileName
            );

            // HTTP so'rov yuborish
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://upload.imagekit.io/api/v1/files/upload"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + auth)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            // Javobdan URL ni olish
            String responseBody = response.body();
            int urlStart = responseBody.indexOf("\"url\":\"") + 7;
            int urlEnd = responseBody.indexOf("\"", urlStart);
            return responseBody.substring(urlStart, urlEnd);

        } catch (Exception e) {
            throw new RuntimeException("Rasm yuklashda xato: " + e.getMessage());
        }
    }
}
