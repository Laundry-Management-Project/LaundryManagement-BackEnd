package project.laundry.data.dto.owner;

import lombok.*;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.responseStatus;

import java.util.List;

@Getter @Setter
public class ownerLoginDto extends responseStatus {


    private List<businessDto> businesses;

    public ownerLoginDto(String message, Boolean status, String uid) {
        super(message, status, uid);
    }
}
