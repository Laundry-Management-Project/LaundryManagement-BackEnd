package project.laundry.dto.post;

import lombok.*;
import project.laundry.entity.status.ClothStatus;

import javax.validation.constraints.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class postDto {

    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" ,message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phone;

    @NotNull
    @PositiveOrZero
    private int clothCount;

    @NotNull @PositiveOrZero
    private int price;

    private ClothStatus clothStatus;

    @NotNull
    @Size(min = 0, max = 999)
    private String content;

    private String createTime;
}
