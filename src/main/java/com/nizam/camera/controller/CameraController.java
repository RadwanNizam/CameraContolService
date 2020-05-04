package com.nizam.camera.controller;

import com.nizam.camera.model.Camera;
import com.nizam.camera.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CameraController {
    @Autowired
    private CameraService cameraService;

    @PostMapping("/camera")
    @CrossOrigin
    public ResponseEntity<Camera> create(@RequestBody Camera camera) {
        cameraService.create(camera);
        return ResponseEntity.ok(camera);
    }

    @PutMapping("/camera")
    public ResponseEntity<Camera> update(@RequestBody Camera camera) {
        cameraService.update(camera);
        return ResponseEntity.ok(camera);
    }

    @DeleteMapping("/camera/{id}")
    public void delete(@PathVariable Long id) {
        cameraService.delete(id);
    }

    @DeleteMapping("/camera")
    public void deleteAll() {
        cameraService.deleteAll();
    }

    @GetMapping("/camera/{id}")
    public ResponseEntity<Camera> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cameraService.find(id));
    }

    @GetMapping("/camera")
    public ResponseEntity<List<Camera>> getAll() {
        return ResponseEntity.ok(cameraService.findAll());
    }
}
