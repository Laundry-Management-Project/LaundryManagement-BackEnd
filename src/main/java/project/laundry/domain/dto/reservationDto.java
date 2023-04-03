package project.laundry.domain.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class reservationDto {

    private String id;

    private String cu_name;

    private String bu_name;

    private String bu_address;
}
