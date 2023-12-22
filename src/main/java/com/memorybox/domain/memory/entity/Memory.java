package com.memorybox.domain.memory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Memory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long cashBoxId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int depositAmount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image",
            joinColumns = @JoinColumn(name = "memory_id"))
    @OrderColumn(name = "line_idx")
    private List<Image> images;

    @Column
    private LocalDate createdAt;

    @Builder
    public Memory(long cashBoxId, String title, String content, int depositAmount, List<String> images, LocalDate createdAt) {
        this.cashBoxId = cashBoxId;
        this.title = title;
        this.content = content;
        this.depositAmount = depositAmount;
        this.createdAt = createdAt;
        saveImages(images);
    }

    private void saveImages(List<String> imageNames) {
        this.images = imageNames.stream()
                .map(Image::new)
                .collect(Collectors.toList());
    }
}