package com.nizam.camera.repository;

import com.nizam.camera.model.Camera;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CameraRepository extends MongoRepository<Camera, Long> {
}
