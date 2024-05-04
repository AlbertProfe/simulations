package com.example.demo.service;

import com.example.demo.model.ImagePlayer;
import com.example.demo.repository.ImagePlayerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagePlayerService {

    @Autowired
    ImagePlayerRepository imagePlayerRepository;

    public List<ImagePlayer> findAllImages (){
        return imagePlayerRepository.findAll();
    }

    public Optional<ImagePlayer> findImageById (String id){
        return imagePlayerRepository.findById(id);
    }

    public ImagePlayer saveImage (ImagePlayer imagePlayer){
        return imagePlayerRepository.save(imagePlayer);
    }

    public void deleteAllImages (){
        imagePlayerRepository.deleteAll();
    }

    public void deleteImageById (String id){
        imagePlayerRepository.deleteById(id);
    }

    public ImagePlayer updateImage(String id, ImagePlayer imagePlayer) {

        // Find the existing ImagePlayer by ID
        Optional<ImagePlayer> existingImagePlayer = imagePlayerRepository.findById(id);
        if (existingImagePlayer.isEmpty()) {
            // If ImagePlayer with the given ID does not exist, return null or throw an exception
            return null;
        } else {

        // Update the fields of the existing ImagePlayer with the new values
        existingImagePlayer.get().setImageName(imagePlayer.getImageName());
        existingImagePlayer.get().setType(imagePlayer.getType());
        existingImagePlayer.get().setSize(imagePlayer.getSize());
        existingImagePlayer.get().setImageData(imagePlayer.getImageData());
        existingImagePlayer.get().setPlayerId(imagePlayer.getPlayerId());

        // Save the updated ImagePlayer
        imagePlayerRepository.save(existingImagePlayer.get());

        return existingImagePlayer.get();
    }
    }

    public List<ImagePlayer> populate (){

        List<ImagePlayer> imagePlayers = new ArrayList<>();

        Faker faker = new Faker();

        for (int i= 0; i < 10; i++){

            ImagePlayer imagePlayer = new ImagePlayer();
            imagePlayer.setId( UUID.randomUUID().toString());
            imagePlayer.setImageName(faker.funnyName().name());
            imagePlayer.setType("BYTE RAW BASE64");
            imagePlayer.setSize(faker.number().randomDouble(2, 100, 300));

            imagePlayers.add(imagePlayer);
            imagePlayerRepository.save(imagePlayer);


        }

        return imagePlayers;

    }

}
