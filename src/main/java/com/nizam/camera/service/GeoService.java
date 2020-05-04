package com.nizam.camera.service;

import com.nizam.camera.model.BearingInfo;
import com.nizam.camera.model.Point;

import java.util.List;

public interface GeoService {
    BearingInfo slewToNearest(Point cameraPoint, List<Point> targetPoints);
}
