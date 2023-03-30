package project.laundry.domain.dto.status;

import lombok.*;
import project.laundry.domain.dto.businessDto;

import java.util.List;

@Getter @Setter
public class ownerResponseStatus extends responseStatus {


    private List<businessDto> businessList;

    public ownerResponseStatus(String message, Boolean status, String uid) {
        super(message, status, uid);
    }
}
