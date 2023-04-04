package project.laundry.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import project.laundry.domain.entity.status.ClothStatus;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class reservation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "re_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private ClothStatus clothStatus;

    private String clothCount;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bu_id")
    private Business business;

}
