package com.memorybox.domain.memory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cash_box_id")
    private long cashBoxId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "deposit_amount")
    private int depositAmount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image",
            joinColumns = @JoinColumn(name = "memory_id"))
    @OrderColumn(name = "line_idx")
    private List<Image> images;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Memory(long cashBoxId, String title, String content, int depositAmount, List<String> images) {
        this.cashBoxId = cashBoxId;
        this.title = title;
        this.content = content;
        this.depositAmount = depositAmount;
        saveImages(images);
    }

    private void saveImages(List<String> imageNames) {
        this.images = imageNames.stream()
                .map(Image::new)
                .collect(Collectors.toList());
    }
}
