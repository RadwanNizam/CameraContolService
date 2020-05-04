package com.nizam.camera.service;

import com.nizam.camera.model.Camera;
import com.nizam.camera.model.SequenceEnum;
import com.nizam.camera.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Camera create(Camera camera) {
        camera.setId(sequenceService.generateSequence(SequenceEnum.CAMERA_SEQUENCE.getSequenceName()));
        camera = cameraRepository.save(camera);
        return camera;
    }

    @Override
    public Camera update(Camera camera) {
        camera = cameraRepository.save(camera);
        return camera;
    }

    @Override
    public void delete(Long id) {
        cameraRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cameraRepository.deleteAll();
    }

    @Override
    public List<Camera> findAll() {
        List<Camera> cameras = cameraRepository.findAll();
        return cameras;
    }

    @Override
    public Camera find(Long id) {
        Camera camera = cameraRepository.findById(id).get();
        return camera;
    }
}
