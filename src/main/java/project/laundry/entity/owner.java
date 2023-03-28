package project.laundry.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class owner {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ow_id")
    private String id;

    @Column(name = "owner_id")
    private String owner_id;

    private String password;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "owner")
    private List<business> businesses = new ArrayList<>();


}
