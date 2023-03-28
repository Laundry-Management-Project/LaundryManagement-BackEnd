package project.laundry.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
public class customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "cu_id")
    private String id;

    @Column(name = "customer_id")
    private String customer_id;

    private String password;

    private String name;

    private String phone;


}
