
package com.example.controller;

import com.example.model.Security;
import com.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {
    @Autowired
    private SecurityService service;

    @PostMapping("/")
    public ResponseEntity<Security> save(@RequestBody Security sec) {
        return ResponseEntity.ok(service.saveSecurity(sec));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Security> get(@PathVariable String id) {
        Security sec = service.getSecurity(id);
        return sec != null ? ResponseEntity.ok(sec) : ResponseEntity.notFound().build();
    }
}
