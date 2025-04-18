package in.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.main.entity.Candidate;
import in.main.entity.Vote;
import in.main.entity.Voter;
import in.main.exception.DuplicateResourceException;
import in.main.exception.ResourceNotFoundException;
import in.main.reposistory.CandidateReposistory;
import in.main.reposistory.VoterReposistory;
import jakarta.transaction.Transactional;

@Service
public class VoterService {
	private VoterReposistory voterRepository;
    private CandidateReposistory candidateRepository;
	
    @Autowired
    public VoterService(VoterReposistory voterRepository, CandidateReposistory candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
    public Voter registerVoter(Voter voter){
   	 if(voterRepository.existsByEmailId(voter.getEmailId())) {
   		 throw new DuplicateResourceException("Voter with email id:"+voter.getEmailId()+" already exists");
   	 }
   	 return voterRepository.save(voter);
    }
    public List<Voter>getAllVoters(){
   	 return voterRepository.findAll();
    }
    public Voter getVoterById(Long id) {
   	 Voter voter=voterRepository.findById(id).orElse(null);
   	 if(voter==null) {
   		 throw new ResourceNotFoundException("Voter not found with id "+id);
   	 }
   	 return voter;
    }
    
    public Voter updateVoter(Long id,Voter updatedVoter) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter with id:"+id+" not found");
		}
		if(updatedVoter.getName()!=null) {
			voter.setName(updatedVoter.getName());
		}
		if(updatedVoter.getEmailId()!=null) {
			voter.setEmailId(updatedVoter.getEmailId());
		}
		return voterRepository.save(voter);		
	}
	
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter=voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Cannot delete voter with id :"+id+" as it doesn not exist");
		}
		Vote vote=voter.getVote();
		if(vote!=null) {
			Candidate candidate=vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepository.save(candidate);
		}		
		voterRepository.delete(voter);
	}
	
	
	
}
