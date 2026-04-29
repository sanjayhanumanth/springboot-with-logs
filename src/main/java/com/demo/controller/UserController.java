package com.demo.controller;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Users", description = "Protected user endpoints")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Returns the currently authenticated user info")
    public ResponseEntity<Map<String, String>> me(@AuthenticationPrincipal UserDetails userDetails) {
        log.debug("Fetching profile for: {}", userDetails.getUsername());
        return ResponseEntity.ok(Map.of(
                "username", userDetails.getUsername(),
                "roles", userDetails.getAuthorities().toString()
        ));
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "List all users (ADMIN only)", description = "Returns all registered users. Requires ADMIN role.")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Admin: fetching all users");
        return ResponseEntity.ok(userRepository.findAll());
    }
}
