package project.laundry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.laundry.data.entity.Post;
import project.laundry.data.entity.status.ClothStatus;

public interface PostRepository extends JpaRepository<Post, String> {

    @Query("SELECT COUNT(p) FROM Post p WHERE p.clothStatus = :status")
    int countClothesStatus(@Param("status") ClothStatus status);
    Page<Post> findTop5ByOrderByIdDesc(Pageable pageable);
}
