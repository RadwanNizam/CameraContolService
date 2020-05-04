package com.nizam.camera.service;

import com.nizam.camera.CameraControlServiceApplication;
import com.nizam.camera.model.BearingInfo;
import com.nizam.camera.model.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CameraControlServiceApplication.class)
@ActiveProfiles("test")
public class TestGeoService {


    @Autowired
    private GeoService geoService;

    @Before
    public void setUp() {
    }

    @Test
    public void testSlewToNearest_positive_coordinates_success() {
        Point startPoint = Point.builder().lat(22.123).lng(53.123).build();
        Point targetPoint = Point.builder().lat(52.0).lng(60.0).build();
        List<Point> targetPoints = Arrays.asList(targetPoint);
        BearingInfo bearingInfo = geoService.slewToNearest(startPoint, targetPoints);
        assertEquals(8.0, bearingInfo.getBearingDegree(), 0.9);
    }

    @Test
    public void testSlewToNearest_negative_lng_success() {
        Point startPoint = Point.builder().lat(25.2854).lng(51.5310).build();
        Point targetPoint = Point.builder().lat(25.2854).lng(-15.0).build();
        List<Point> targetPoints = Arrays.asList(targetPoint);
        BearingInfo bearingInfo = geoService.slewToNearest(startPoint, targetPoints);
        assertEquals(285.0, bearingInfo.getBearingDegree(), 0.9);
    }

    @Test
    public void testSlewToNearest_negative_lat_success() {
        Point startPoint = Point.builder().lat(25.2854).lng(51.5310).build();
        Point targetPoint = Point.builder().lat(-25.2854).lng(51.5310).build();
        List<Point> targetPoints = Arrays.asList(targetPoint);
        BearingInfo bearingInfo = geoService.slewToNearest(startPoint, targetPoints);
        assertEquals(180, bearingInfo.getBearingDegree(), 0.9);
    }

    @Test
    public void testSlewToNearest_nearest_among_list_success() {
        Point startPoint = Point.builder().lat(25.2931).lng(51.491365).build();
        Point point1 = Point.builder().lat(25.294).lng(51.4918).build();
        Point point2 = Point.builder().lat(25.294).lng(51.4914).build();
        Point nearestPoint = Point.builder().lat(25.2932).lng(51.491369).build(); // nearest
        Point point4 = Point.builder().lat(25.292).lng(51.4918).build();

        List<Point> targetPoints = Arrays.asList(point1, point2, nearestPoint, point4);
        BearingInfo bearingInfo = geoService.slewToNearest(startPoint, targetPoints);

        assertEquals(startPoint.getLat(), bearingInfo.getFrom().getLat());
        assertEquals(startPoint.getLng(), bearingInfo.getFrom().getLng());
        assertEquals(nearestPoint.getLat(), bearingInfo.getTo().getLat());
        assertEquals(nearestPoint.getLng(), bearingInfo.getTo().getLng());
        assertEquals(2, bearingInfo.getBearingDegree(), 0.09);
    }
}
