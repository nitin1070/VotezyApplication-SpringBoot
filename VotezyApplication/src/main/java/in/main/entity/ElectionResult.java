package in.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ElectionResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String electionName;
	@OneToOne
	@JoinColumn(name = "winner_id")
	@JsonIgnore // JsonIgnore to avoid get full candidate obj 
	private Candidate winner;
	private int totalVotes;
	@JsonProperty  // jackson allow to get this value 
	public Long getWinnerId() {
		return winner!=null?winner.getId():null;
	}

}
