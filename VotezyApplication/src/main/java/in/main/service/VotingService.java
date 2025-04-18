package in.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.main.entity.Candidate;
import in.main.entity.Vote;
import in.main.entity.Voter;
import in.main.exception.ResourceNotFoundException;
import in.main.exception.VoteNotAllowedException;
import in.main.reposistory.CandidateReposistory;
import in.main.reposistory.VoteReposistory;
import in.main.reposistory.VoterReposistory;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
	private VoteReposistory voteReposistory;
	private CandidateReposistory candidateReposistory;
	private VoterReposistory voterReposistory;

	@Autowired
	public VotingService(VoteReposistory voteReposistory, CandidateReposistory candidateReposistory,
			VoterReposistory voterReposistory) {
		this.voteReposistory = voteReposistory;
		this.candidateReposistory = candidateReposistory;
		this.voterReposistory = voterReposistory;
	}

	@Transactional
	public Vote casteVote(Long voterId, Long candidateId) {
		if (!voterReposistory.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter Not Found With Id" + voterId);
		}
		if (!candidateReposistory.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate Not Found With Id" + candidateId);
		}
		Voter voter = voterReposistory.findById(voterId).get();
		if (voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter id" + voterId + "has already voted !");
		}

		Candidate candidate = candidateReposistory.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
		 voteReposistory.save(vote); 
		
		 candidate.setVoteCount(candidate.getVoteCount() + 1);
		candidateReposistory.save(candidate);
		/* voter.setVote(vote); */ // optional way to save vote
		voter.setHasVoted(true);
		voterReposistory.save(voter);
		return vote;

	}

	public List<Vote> getAllVotes() {
		return voteReposistory.findAll();
	}

}
