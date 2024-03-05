package com.example.UserService.controller;

import com.example.UserService.client.ChatFeignClient;
import com.example.UserService.client.request.ContactRequest;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.KeycloaksInfo;
import com.example.UserService.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    private final ChatFeignClient client;

    @GetMapping
    public KeycloaksInfo hello() {
        return testService.test("eyJleHAiOjE3MDI1NjUwMDMsImlhdCI6MTcwMjU2NDk0MywianRpIjoiYzU2ZGY4MDYtNTJiNi00OTEzLTk5MDgtZDAwYjZiNGM0NGE0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo3MDgwL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZjk2NTlmZTUtMmFiNi00YjU0LWJjMGYtNjdhODFiNGFkNjA4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiSHVzdEFwcCIsInNlc3Npb25fc3RhdGUiOiIwMzE0NWEyNS0wNWZiLTQ3N2ItODgzMS00MTZkMDg4NGU0YmMiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIi8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW1hc3RlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJVU0VSIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiMDMxNDVhMjUtMDVmYi00NzdiLTg4MzEtNDE2ZDA4ODRlNGJjIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0dXllbmhhaXRhbkBnbWFpbC5jb20iLCJlbWFpbCI6InR1eWVuaGFpdGFuQGdtYWlsLmNvbSJ9");
    }
    @PostMapping ("/feign")
    public MessagesResponse getFeign(@RequestBody ContactRequest contactRequest) {
        MessagesResponse ms = client.createContact(contactRequest);
        return ms;
    }

}
