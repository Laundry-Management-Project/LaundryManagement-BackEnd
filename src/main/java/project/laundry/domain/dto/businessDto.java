package project.laundry.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.laundry.domain.entity.Owner;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class businessDto {

    private String id;

    private String name;

    private String address;

    private String bu_hour;
}
