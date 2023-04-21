package project.laundry.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.laundry.data.response.common.BusinessDto;

import java.util.List;

@Getter @Setter
@Builder
public class BusinessDtoList {

    private List<BusinessDto> businesses;
}
