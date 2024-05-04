package com.example.demo.model;
import org.bson.types.Binary;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "imagePlayer")
public class ImagePlayer {

    @Id
    private String id;
    private String imageName;
    private String type;
    private Double size;
    private Binary imageData;
    private String playerId;

}
