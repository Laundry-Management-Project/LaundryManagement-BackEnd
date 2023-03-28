package project.laundry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

// 사업징
@Entity
public class business {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "bu_id")
    private String id;

    private String name;

    private String address;

    // 영업시간  -> 07:00 오전 ~ 11:00 오후
    private String bu_hour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ow_id")
    private owner owner;

}
