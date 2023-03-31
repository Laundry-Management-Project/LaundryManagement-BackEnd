package project.laundry.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

// 사업징
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Business {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "bu_id")
    private String uid;

    private String name;

    private String address;

    // 영업시간  -> 07:00 ~ 17:00
    private String bu_hour;

    // 사장님 uid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ow_id")
    private Owner owner;

}
