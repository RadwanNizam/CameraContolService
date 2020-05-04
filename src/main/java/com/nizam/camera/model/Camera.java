package com.nizam.camera.model;

import com.nizam.camera.config.MongoCollections;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = MongoCollections.CAMERA_COLLECTION)
public class Camera {
    @Id
    private Long id;
    @Indexed(name = "camera_name", unique = true)
    @NotNull
    private String name;
    @NotNull
    private CameraType type;
    @Min(-90)
    @Max(90)
    @NotNull
    private Double lat;
    @Min(-180)
    @Max(180)
    @NotNull
    private Double lng;
    @Min(0)
    @Max(360)
    private Double azimuth;
}
