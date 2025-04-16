package in.main.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.Candidate;

public interface CandidateReposistory extends JpaRepository<Candidate, Long>{
      List<Candidate>findAllByOrderByVoteCountDesc();
}
