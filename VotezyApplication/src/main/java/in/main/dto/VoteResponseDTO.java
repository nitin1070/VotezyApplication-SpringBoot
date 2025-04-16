package in.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
	private String message;
	private boolean success;
	private Long voterId;
	private Long candidateId;

}
