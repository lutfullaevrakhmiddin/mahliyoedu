package uz.mahliyoedu.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.Base64;

@Service
public class ImageKitService {

    @Value("${imagekit.private-key}")
    private String privateKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String uploadImage(MultipartFile file, String fileName) {
        try {
            String auth = Base64.getEncoder()
                .encodeToString((privateKey + ":").getBytes());

            String boundary = "----FormBoundary" + System.currentTimeMillis();

            // Multipart body yaratish
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // fileName field
            baos.write(("--" + boundary + "\r\n").getBytes());
            baos.write("Content-Disposition: form-data; name=\"fileName\"\r\n\r\n".getBytes());
            baos.write((fileName + "\r\n").getBytes());

            // folder field
            baos.write(("--" + boundary + "\r\n").getBytes());
            baos.write("Content-Disposition: form-data; name=\"folder\"\r\n\r\n".getBytes());
            baos.write("/mahliyoedu/\r\n".getBytes());

            // file field
            baos.write(("--" + boundary + "\r\n").getBytes());
            baos.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"\r\n").getBytes());
            baos.write(("Content-Type: " + file.getContentType() + "\r\n\r\n").getBytes());
            baos.write(file.getBytes());
            baos.write("\r\n".getBytes());

            // End boundary
            baos.write(("--" + boundary + "--\r\n").getBytes());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://upload.imagekit.io/api/v1/files/upload"))
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .header("Authorization", "Basic " + auth)
                .POST(HttpRequest.BodyPublishers.ofByteArray(baos.toByteArray()))
                .build();

            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            System.out.println("ImageKit response: " + response.body());

            JsonNode json = objectMapper.readTree(response.body());
            if (json.has("url")) {
                return json.get("url").asText();
            }
            throw new RuntimeException("ImageKit xato: " + response.body());

        } catch (Exception e) {
            throw new RuntimeException("Rasm yuklashda xato: " + e.getMessage());
        }
    }
}
