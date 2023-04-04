package project.laundry.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uid")
    private String id;

    @Column(name = "customer_id")
    private String customer_id;

    private String password;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Reservation> Reservations = new ArrayList<>();

}
