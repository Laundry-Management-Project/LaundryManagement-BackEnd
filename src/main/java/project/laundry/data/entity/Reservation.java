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

    @Enumerated(EnumType.STRING)
    private ClothStatus cloth_status;

    private String request_detail;

    private String clothing_type;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_uid")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

}
