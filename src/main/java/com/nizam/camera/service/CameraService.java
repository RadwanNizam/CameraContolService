package com.nizam.camera.service;

import com.nizam.camera.model.Camera;

import java.util.List;

public interface CameraService {
    public Camera create(Camera camera);

    public Camera update(Camera camera);

    public void delete(Long id);

    public void deleteAll();

    public List<Camera> findAll();

    public Camera find(Long id);
}
