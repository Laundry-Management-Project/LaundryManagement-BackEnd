package project.laundry.domain.dto.status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.laundry.domain.dto.businessDto;

import java.util.List;

@Getter @Setter
public class customerResponseStatus extends responseStatus {

    List<businessDto> businessList;

    public customerResponseStatus(String message, boolean status, String uid) {
        super(message, status, uid);
    }
}
