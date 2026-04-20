package com.example.demo;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class WebhookService {

    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        // Step 1: Generate webhook
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> body = new HashMap<>();
        body.put("name", "Shuchi Mishra");
        body.put("regNo", "YOUR_REG_NO");
        body.put("email", "YOUR_EMAIL");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        String token = (String) response.getBody().get("accessToken");

        // Step 2: Send final SQL
        String finalUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.set("Authorization", "Bearer " + token);

        Map<String, String> sqlBody = new HashMap<>();
        sqlBody.put("finalQuery",
                "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE DAY(p.PAYMENT_TIME) != 1 ORDER BY p.AMOUNT DESC LIMIT 1;"
        );

        HttpEntity<Map<String, String>> finalRequest = new HttpEntity<>(sqlBody, headers2);

        restTemplate.postForEntity(finalUrl, finalRequest, String.class);
    }
}
