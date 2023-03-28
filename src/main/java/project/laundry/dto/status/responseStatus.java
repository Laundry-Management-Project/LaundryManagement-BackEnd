package project.laundry.dto.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class responseStatus {
    private String message;
    private Boolean status;

    private String id;

    public responseStatus(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}
