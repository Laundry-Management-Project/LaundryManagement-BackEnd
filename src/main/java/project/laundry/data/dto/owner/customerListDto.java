package project.laundry.data.dto.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class customerListDto {

    private String name;

    private String phone;
}
