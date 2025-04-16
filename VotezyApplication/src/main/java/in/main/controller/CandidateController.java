package in.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import in.main.entity.Candidate;
import in.main.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
	private CandidateService candidateService;

	@Autowired
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	@PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
		Candidate savedCandidate=candidateService.addCandidate(candidate);
		return new ResponseEntity<Candidate>(savedCandidate,HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidateList=this.candidateService.getAllCandidates();
		return new ResponseEntity<List<Candidate>>(candidateList,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id){
		Candidate candidate=this.candidateService.getCandidateById(id);
		return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate>updateCandidate(@PathVariable("id")Long id, @RequestBody  Candidate updatedCandidate){
		Candidate candidate=this.candidateService.updateCandidate(id, updatedCandidate);
		
		return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteCandidate(@PathVariable("id")Long id){
		candidateService.deleteCandidate(id);
		return new ResponseEntity<String>("Candidate with ID"+id +"deleted seccessfully ",HttpStatus.OK);
	}
	
}
