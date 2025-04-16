package in.main.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.Voter;

public interface VoterReposistory extends JpaRepository<Voter, Long>{
      boolean existsByEmailId(String email);

}
