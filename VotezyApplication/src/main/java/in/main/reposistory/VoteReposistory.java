package in.main.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.Vote;

public interface VoteReposistory extends JpaRepository<Vote, Long> {

}
