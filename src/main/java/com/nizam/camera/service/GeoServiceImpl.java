package com.nizam.camera.service;

import com.nizam.camera.model.BearingInfo;
import com.nizam.camera.model.Point;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.List;

@Service
public class GeoServiceImpl implements GeoService {


    @Override
    public BearingInfo slewToNearest(Point cameraPoint, List<Point> targetPoints) {
        if (targetPoints.isEmpty()) {
            throw new RuntimeException("points should not be empty");
        }

        Point nearestPoint = findNearestPoint(cameraPoint, targetPoints);
        Double lng1 = cameraPoint.getLng();
        Double lat1 = cameraPoint.getLat();
        Double lng2 = nearestPoint.getLng();
        Double lat2 = nearestPoint.getLat();

        double degToRad = Math.PI / 180.0;
        double lat1Rad = lat1 * degToRad;
        double lat2Rad = lat2 * degToRad;
        double lng1Rad = lng1 * degToRad;
        double lng2Rad = lng2 * degToRad;

        Double initialBearingInDegree = Math.atan2(Math.sin(lng2Rad - lng1Rad) * Math.cos(lat2Rad),
                Math.cos(lat1Rad) * Math.sin(lat2Rad) -
                        Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(lng2Rad - lng1Rad)) * 180 / Math.PI;
        initialBearingInDegree = initialBearingInDegree < 0 ? initialBearingInDegree + 360 : initialBearingInDegree;
        BearingInfo bearingInfo = BearingInfo.builder().from(cameraPoint).to(nearestPoint).bearingDegree(initialBearingInDegree).build();
        return bearingInfo;
    }

    private Point findNearestPoint(Point startingPoint, List<Point> targetPoints) {
        Point nearestPoint = null;
        Double nearestDistance = Double.MAX_VALUE;

        for (Point point : targetPoints) {
            Double distanceToMainPoint = Point2D.distance(startingPoint.getLng(), startingPoint.getLat(), point.getLng(), point.getLat());
            if (distanceToMainPoint < nearestDistance) {
                nearestDistance = distanceToMainPoint;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }
}
