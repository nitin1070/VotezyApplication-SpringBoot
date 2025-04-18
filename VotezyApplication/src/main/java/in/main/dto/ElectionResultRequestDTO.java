package in.main.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResultRequestDTO {
	@NotBlank(message = "Election name required !")
	private String electionName;

}
