package project.laundry.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;

    private String StoreName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<customer> customerList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Visit diary;
}
