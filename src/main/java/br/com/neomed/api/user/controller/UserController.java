package br.com.neomed.api.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("User list allowed");
    }

    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    public ResponseEntity<String> create() {
        return ResponseEntity.ok("User creation allowed");
    }
}