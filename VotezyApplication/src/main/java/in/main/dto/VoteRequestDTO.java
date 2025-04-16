package in.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class VoteRequestDTO {
	    @NotNull( message = "voter id is required")
         Long voterId;
	    @NotNull(message = "candidate id is required")
         Long candidateId;
}
