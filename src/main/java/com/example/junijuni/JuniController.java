package com.example.junijuni;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JuniController {
    private final JuniService juniService;

    @PostMapping
    public ResponseEntity<Void> saveData (@RequestBody Dto data) {
        juniService.execute(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Optional<Dto>> getData() {
        Optional<Dto> data = juniService.dataGet();
        return ResponseEntity.ok(data);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteData() {
        juniService.dataDelete();
        return ResponseEntity.ok().build();
    }
}
