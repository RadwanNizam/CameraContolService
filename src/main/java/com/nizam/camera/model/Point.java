package com.nizam.camera.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Point implements Serializable {
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
}
