package project.laundry.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import project.laundry.data.common.ClothStatus;
import project.laundry.data.common.TimeEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class Reservation extends TimeEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "re_id")
    private String id;

    private String cu_name;

    private String bu_name;

    @Enumerated(EnumType.STRING)
    private ClothStatus clothStatus;

    private String clothCount;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_uid")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

}
