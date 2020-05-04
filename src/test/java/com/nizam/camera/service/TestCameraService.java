package com.nizam.camera.service;

import com.nizam.camera.CameraControlServiceApplication;
import com.nizam.camera.model.Camera;
import com.nizam.camera.model.CameraType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CameraControlServiceApplication.class)
@ActiveProfiles("test")
public class TestCameraService {

    private static final Double DOHA_LNG = 51.5310;
    private static final Double DOHA_LAT = 25.2854;
    private static final Double AZIMUTH = 90D;

    private static int sequence = 1;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private CameraService cameraService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
    }

    @Test
    public void testCreateCamera_success() {
        String name = getUniqueName();
        Camera camera = getDefaultCamera(name);

        camera = cameraService.create(camera);

        assertNotNull(camera.getId());
        assertEquals(name, camera.getName());
        assertEquals(CameraType.FRONT, camera.getType());
        assertEquals(DOHA_LNG, camera.getLng());
        assertEquals(DOHA_LAT, camera.getLat());
        assertEquals(AZIMUTH, camera.getAzimuth());
    }

    @Test
    public void testCreateCamera_two_objects_success() {
        Camera camera1 = getDefaultCamera(getUniqueName());
        camera1 = cameraService.create(camera1);
        Camera camera2 = getDefaultCamera(getUniqueName());
        camera2 = cameraService.create(camera2);
        assertNotEquals(camera1.getId(), camera2.getId());
    }

    @Test
    public void testCreateCamera_two_objects_same_name_fail() {
        exception.expect(DuplicateKeyException.class);
        exception.expectMessage(Matchers.containsString("index: camera_name"));

        String name = getUniqueName();
        Camera camera1 = getDefaultCamera(name);
        camera1 = cameraService.create(camera1);
        cameraService.create(camera1);
        Camera camera2 = getDefaultCamera(name);
        camera2 = cameraService.create(camera2);
        cameraService.create(camera2);
    }

    @Test
    public void testCreateCamera_missing_name_fail() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage(Matchers.containsString("name: must not be null"));

        Camera camera = getDefaultCamera(null);
        cameraService.create(camera);
    }

    @Test
    public void testCreateCamera_missing_type_fail() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage(Matchers.containsString("type: must not be null"));

        Camera camera = getDefaultCamera(getUniqueName());
        camera.setType(null);
        cameraService.create(camera);
    }

    @Test
    public void testCreateCamera_missing_lng_fail() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage(Matchers.containsString("lng: must not be null"));

        Camera camera = getDefaultCamera(getUniqueName());
        camera.setLng(null);
        cameraService.create(camera);
    }

    @Test
    public void testCreateCamera_missing_lat_fail() {
        exception.expect(ConstraintViolationException.class);
        exception.expectMessage(Matchers.containsString("lat: must not be null"));

        Camera camera = getDefaultCamera(getUniqueName());
        camera.setLat(null);
        cameraService.create(camera);
    }

    private Camera getDefaultCamera(String name) {
        return Camera.builder().name(name).type(CameraType.FRONT).
                lng(DOHA_LNG).lat(DOHA_LAT).azimuth(AZIMUTH).build();
    }

    private String getUniqueName() {
        return "camera_" + sequence++;
    }
}
