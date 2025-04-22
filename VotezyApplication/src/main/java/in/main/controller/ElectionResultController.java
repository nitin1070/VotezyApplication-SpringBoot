package in.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.main.dto.ElectionResultRequestDTO;
import in.main.dto.ElectionResultResponseDTO;
import in.main.entity.ElectionResult;
import in.main.service.ElectionResultService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {
	@Autowired
	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}



	private ElectionResultService electionResultService;
	
	
	@PostMapping("/declare")
  public ResponseEntity<ElectionResultResponseDTO>declareElectionResult(@RequestBody  @Valid ElectionResultRequestDTO electionResultRequestDTO){
	  ElectionResult electionResult = electionResultService.declareElectionResult(electionResultRequestDTO.getElectionName());
	  
	  ElectionResultResponseDTO electionResultResponseDTO = new ElectionResultResponseDTO();
	  electionResultRequestDTO.setElectionName(electionResult.getElectionName());
	  electionResultResponseDTO.setTotalVotes(electionResult.getTotalVotes());
	  electionResultResponseDTO.setWinnerId(electionResult.getWinnerId());
	  electionResultResponseDTO.setWinnerVotes(electionResult.getWinner().getVoteCount());
	  
	  return ResponseEntity.ok(electionResultResponseDTO);
  }
	@GetMapping
	public ResponseEntity<List<ElectionResult>>getAllResults(){
		  List<ElectionResult>results = electionResultService.getAllResult();
		return ResponseEntity.ok(results);
		
	}
}
