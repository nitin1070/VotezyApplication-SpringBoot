package in.main.reposistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.ElectionResult;

public interface ElectionResultReposistory extends JpaRepository<ElectionResult, Long>{
	Optional<ElectionResult>findByElectionName(String electionName);

}
