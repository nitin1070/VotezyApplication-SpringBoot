package in.main.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import in.main.entity.Candidate;
import in.main.entity.ElectionResult;
import in.main.reposistory.CandidateReposistory;
import in.main.reposistory.ElectionResultReposistory;
import in.main.reposistory.VoteReposistory;

@Service
public class ElectionResultService {
	@Autowired
	public ElectionResultService(ElectionResultReposistory electionResultReposistory,
			CandidateReposistory candidateReposistory, VoteReposistory voteReposistory) {
		
		this.electionResultReposistory = electionResultReposistory;
		this.candidateReposistory = candidateReposistory;
		this.voteReposistory = voteReposistory;
	}
	private ElectionResultReposistory electionResultReposistory;
	private CandidateReposistory candidateReposistory;
	private VoteReposistory voteReposistory;
	
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult>existingResult = this.electionResultReposistory.findByElectionName(electionName);
		if(existingResult.isPresent()) {
			existingResult.get();
		
		}
		if(voteReposistory.count()==0) {
			throw new IllegalStateException("cannot be declare result as no vote casted now !");
		}
		List<Candidate>allCandidates=candidateReposistory.findAllByOrderByVoteCountDesc();
		if(allCandidates.isEmpty()) {
			throw new ResourceClosedException("No candidate found");
		}
		Candidate winner =allCandidates.get(0);
	  int totalVotes=0;
	  for(Candidate candidate:allCandidates) {
		  totalVotes+=candidate.getVoteCount();
	  }
	  ElectionResult result = new ElectionResult();
	  result.setElectionName(electionName);
	  result.setTotalVotes(totalVotes);
	  result.setWinner(winner);
		
		return electionResultReposistory.save(result);
		
	
	}
	
	
	
	public List<ElectionResult>getAllResult(){
		return electionResultReposistory.findAll();
	}

}
