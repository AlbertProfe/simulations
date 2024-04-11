package com.example.demo.controller;

import com.example.demo.model.Simulation;
import com.example.demo.repository.SimulationRepository;
import com.example.demo.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/simulation/")
public class SimulationRestController {

    @Autowired
    SimulationService simulationService;

    @Autowired
    SimulationRepository simulationRepository;

    @RequestMapping("/hello")
    public String HelloWorld (){
        return "This is a sandbox for my first java class controller";
    }

    //CRUD: read
    @RequestMapping("/simulations")
    public Iterable<Simulation> getAllSimulations (){

        return simulationRepository.findAll();

    }

    //CRUD: delete
    @DeleteMapping("/delete")
    public String deleteSimulation(@RequestParam String id) {

        //System.out.println("id:" + id);
        Optional<Simulation> simulationFound = simulationRepository.findById(id);
        long countBefore = simulationRepository.count();
        //System.out.println("simulationFound:" + simulationFound);

        if (simulationFound.isPresent()){
            simulationRepository.deleteById(id);
            long countAfter = simulationRepository.count();
            String response = "simulation deleted: " + id + " count: " + countBefore + "/" + countAfter;
            return response;
        } else return "id: " + id +  " not found " + " count: " + countBefore;

    }

    //CRUD: create
    @PostMapping(path="create", consumes = "application/JSON")
    public Simulation createSimulation(@RequestBody Simulation simulation){
        //
        Simulation simulationCreated = simulationRepository.save(simulation);
        return simulationCreated;
    }
    
    //CRUD: update
    @PutMapping("/update/{id}")
    public Simulation updateSimulation (@PathVariable String id, @RequestBody Simulation simulation) {

        Optional<Simulation> simulationFound = simulationRepository.findById(id);

        if (simulationFound.isPresent()) {
            Simulation simulationToUpdate = simulationFound.get();
            //
            if  (simulation.getTimeElapsed() > 0) {
                simulationToUpdate.setTimeElapsed(simulation.getTimeElapsed());
            }

            Simulation simulationUpdated = simulationRepository.save(simulationToUpdate);
            return simulationUpdated;
        } else
            return null;

    }

    @RequestMapping("/populate")
    public String populateSimulationDB(){

        simulationService.populate();

        return "ok";
    }





}
