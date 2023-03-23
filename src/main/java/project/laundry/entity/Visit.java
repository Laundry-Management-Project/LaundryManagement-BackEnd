package project.laundry.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "visit")
@Builder
public class Visit extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;

    private int price;

    private LocalDate visitDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Visit of(Post post, int price) {
        return Visit.builder()
                .post(post)
                .visitDateTime(LocalDate.now())
                .price(price)
                .build();
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
