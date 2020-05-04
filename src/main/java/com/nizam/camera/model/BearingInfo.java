package com.nizam.camera.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BearingInfo implements Serializable {
    Point from;
    Point to;
    Double bearingDegree;
}
