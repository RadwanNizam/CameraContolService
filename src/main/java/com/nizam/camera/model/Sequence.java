package com.nizam.camera.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "database_sequences")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {

    @Id
    private String id;
    private long seq;
}