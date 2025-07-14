package com.example.jwt.controller;

import com.example.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/authenticate")
    public ResponseEntity<?> generateToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }
        String[] tokens = extractUsernameAndPassword(header);
        String username = tokens[0];
        String password = tokens[1];
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }

    private String[] extractUsernameAndPassword(String header) {
        String base64Credentials = header.substring("Basic ".length());
        byte[] decoded = Base64.getDecoder().decode(base64Credentials);
        String decodedString = new String(decoded);
        return decodedString.split(":", 2);
    }
}