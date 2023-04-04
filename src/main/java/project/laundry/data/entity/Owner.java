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
public class Owner {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uid")
    private String id;

    @Column(name = "owner_id")
    private String owner_id;

    private String password;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private final List<Business> businesses = new ArrayList<>();


}
