package project.laundry.entity;

import javax.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private String createTime;

    @LastModifiedDate
    private String updateTime;

    // Builder는 @PrePersist 이벤트를 통해 엔티티를 지정할 때 호출되는 콜백 메서드를 자동으로 생성하지 않음
    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.updateTime = this.createTime;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}
