package project.laundry.domain.dto.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class responseStatus {

    private String message;

    private Boolean status;

    private String uid;
}
