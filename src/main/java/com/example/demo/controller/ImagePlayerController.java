package com.example.demo.controller;

import com.example.demo.model.ImagePlayer;
import com.example.demo.model.Player;
import com.example.demo.repository.ImagePlayerRepository;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.ImagePlayerService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/imagePlayer")
public class ImagePlayerController {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ImagePlayerRepository imagePlayerRepository;
    @Autowired
    ImagePlayerService imagePlayerService;

    //https://github.com/AlbertProfe/CifoJava2023/blob/1ae3bdbc654df8f25658acf177e8d6b65e6c6c55/LibraryManagementRest/src/main/java/com/example/myFirstSpring/restcontroller/BookImageRestController.java
    @PostMapping("/uploadImage")
    public ResponseEntity<ImagePlayer> uploadImage(@RequestParam String playerId,
                                                   @RequestParam String imageName,
                                                   @RequestParam MultipartFile file) throws IOException {
        //to do: add headers
        //HttpHeaders headers = new HttpHeaders();
        // Finds a playerId in the player repository by its ID, if present.
        Optional<Player> player = playerRepository.findById(playerId);
        //check playerId exist ....
        if (player.isPresent()) {
            //create object
            ImagePlayer imagePlayer = new ImagePlayer();
            // create UUID and set object
            String imagePlayerId = UUID.randomUUID().toString();
            imagePlayer.setId(imagePlayerId);
            imagePlayer.setPlayerId(playerId);
            imagePlayer.setImageName(imageName);
            //set file to field
            //to do: try & catch
            imagePlayer.setImageData(new Binary(file.getBytes()));
            //assign UUID imagePlayer created to player within ids list
            player.get().addImagePlayerId(imagePlayerId);
            //save Document to mongoDB
            imagePlayerRepository.save(imagePlayer);
            //save Entity to H2 local
            playerRepository.save(player.get());

            //return imagePlayer to API rest
            return ResponseEntity.accepted().body(imagePlayer);

        } else {
            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/getImageById")
    public ResponseEntity<byte[]> getImage(@RequestParam String id){

        Optional<ImagePlayer> imagePlayer = imagePlayerRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        if (imagePlayer.isPresent())
            return new ResponseEntity<>( imagePlayer.get().getImageData().getData(), headers, HttpStatus.OK );
        else  return ResponseEntity.notFound().build();
    }


    @GetMapping
    public List<ImagePlayer> getAllImagePlayers() {
        return imagePlayerService.findAllImages();
    }

    @GetMapping("findById/{id}")
    public ImagePlayer getImagePlayerById(@PathVariable("id") String id) {
        return imagePlayerService.findImageById(id).get();
    }

    @PostMapping("create/{id}")
    public ImagePlayer createImagePlayer(@RequestBody ImagePlayer imagePlayer) {
        return imagePlayerService.saveImage(imagePlayer);
    }

    @PutMapping("update/{id}")
    public ImagePlayer updateImagePlayer(@PathVariable("id") String id, @RequestBody ImagePlayer imagePlayer) {
        return imagePlayerService.updateImage(id, imagePlayer);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteImageById(@PathVariable("id") String id) {
        imagePlayerService.deleteImageById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllImagePlayers() {
        imagePlayerService.deleteAllImages();
    }
}
