package project.laundry.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Business {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "business_id")
    private String uid;

    private String name;

    private String address;

    // 영업시간  -> 07:00 ~ 17:00
    private String bu_hour;

    private String contact;

    private String intro;

    // 사장님 uid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_uid")
    private Owner owner;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

}
