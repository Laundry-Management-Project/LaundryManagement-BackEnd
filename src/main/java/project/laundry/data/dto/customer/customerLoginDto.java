package project.laundry.data.dao.customer;

import lombok.Getter;
import lombok.Setter;
import project.laundry.data.dao.common.businessDao;
import project.laundry.data.dao.common.reservationDao;
import project.laundry.data.dao.responseStatus;

import java.util.List;

@Getter @Setter
public class customerLoginDao extends responseStatus {

    List<reservationDao> reservations;

    List<businessDao> businesses;

    public customerLoginDao(String message, boolean status, String uid) {
        super(message, status, uid);
    }
}
