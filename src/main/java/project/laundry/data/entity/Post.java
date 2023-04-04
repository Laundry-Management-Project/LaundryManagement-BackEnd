package project.laundry.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import project.laundry.data.dto.common.postDto;
import project.laundry.data.entity.status.ClothStatus;

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
    @Column(name = "id")
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
