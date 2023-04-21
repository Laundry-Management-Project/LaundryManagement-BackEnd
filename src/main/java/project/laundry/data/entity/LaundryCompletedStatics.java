package project.laundry.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import project.laundry.data.common.TimeEntity;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaundryCompletedStatics extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String clothCount;

    private String content;

    private String price;

    private String completeTime = getCreateTime();

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Override
    public String getCreateTime() {
        return super.getCreateTime();
    }
}
