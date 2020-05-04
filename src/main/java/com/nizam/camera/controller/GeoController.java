package com.nizam.camera.controller;

import com.nizam.camera.model.BearingInfo;
import com.nizam.camera.model.BearingRequest;
import com.nizam.camera.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo")
@CrossOrigin
public class GeoController {

    @Autowired
    private GeoService geoService;

    @PostMapping("/nearest")
    public ResponseEntity<BearingInfo> slewToNearest(@RequestBody BearingRequest bearingRequest) {
        return ResponseEntity.ok(geoService.slewToNearest(bearingRequest.getCameraPoint(), bearingRequest.getTargetPoints()));
    }

}
