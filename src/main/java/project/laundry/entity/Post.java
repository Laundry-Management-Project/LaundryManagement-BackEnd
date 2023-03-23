package project.laundry.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import project.laundry.dto.post_dto.postDto;
import project.laundry.entity.status.ClothStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "post")
@Builder
public class Post extends TimeEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "post_id")
    private String id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    private int clothCount;

    private int price;

    @Enumerated(EnumType.STRING)
    private ClothStatus clothStatus;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private owner owner;


    @Builder.Default
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    public String updatePost(postDto dto) {
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.clothCount = dto.getClothCount();
        this.price = dto.getPrice();
        this.content = dto.getContent();
        this.clothStatus = dto.getClothStatus();

        return this.id;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPost(this);
    }

}
