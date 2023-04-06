package project.laundry.data.dto.common;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class reservationDto {

    private String id;

    private String bu_id;

    private String cu_name;

    private String bu_name;

    private String bu_address;

    private String clothCount;

    private String clothStatus;

    private String content;

    private String createdAt;
}
