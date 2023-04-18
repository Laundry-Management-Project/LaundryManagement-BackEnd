package project.laundry.data.dao.owner;

import lombok.*;
import project.laundry.data.dao.common.businessDao;
import project.laundry.data.dao.responseStatus;

import java.util.List;

@Getter @Setter
public class ownerLoginDto extends responseStatus {


    private List<businessDao> businesses;

    public ownerLoginDto(String message, Boolean status, String uid) {
        super(message, status, uid);
    }
}
