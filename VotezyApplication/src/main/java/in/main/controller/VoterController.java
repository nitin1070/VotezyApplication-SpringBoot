package in.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.main.entity.Voter;
import in.main.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
private VoterService voterService;
    
    @Autowired
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
    @PostMapping("/register")
     public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
    	 Voter savedVoter=voterService.registerVoter(voter);
    	 return new ResponseEntity<>(savedVoter,HttpStatus.CREATED);
     }
    @GetMapping("/{id}")
     public ResponseEntity<Voter>getVoterById(@PathVariable("id") Long id){
    	 Voter voter=voterService.getVoterById(id);
    	 return new ResponseEntity<>(voter,HttpStatus.OK);
     }
    @GetMapping
    public ResponseEntity<List<Voter>> getAllVoters(){
    	List<Voter>voterList=voterService.getAllVoters();
   	 return new ResponseEntity<>(voterList,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable("id") Long id,@RequestBody Voter voter){
    	Voter updatedVoter=voterService.updateVoter(id, voter);
    	return new ResponseEntity<>(updatedVoter,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable("id") Long id){
    	voterService.deleteVoter(id);
    	return new ResponseEntity<>("Voter with id:"+id+" deleted",HttpStatus.OK);
    }
}
