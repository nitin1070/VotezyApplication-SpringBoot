package in.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ElectionResultRequestDTO {
	@NotBlank(message = "Election name required !")
	private String electionName;

}
