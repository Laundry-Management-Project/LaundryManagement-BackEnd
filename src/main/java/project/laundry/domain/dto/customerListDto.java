package project.laundry.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class customerListDto {

    private String name;

    private String phone;
}
