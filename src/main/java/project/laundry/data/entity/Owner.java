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
    @Column(name = "owner_uid")
    private String uid;

    @Column(name = "owner_id", unique = true)
    private String owner_id;

    private String password;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private final List<Business> businesses = new ArrayList<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Owner_Authority> roles = new ArrayList<>();

    public void setRoles(List<Owner_Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setOwner(this));
    }

}
