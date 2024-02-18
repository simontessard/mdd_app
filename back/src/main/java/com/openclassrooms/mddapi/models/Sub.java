package com.openclassrooms.mddapi.models;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subs")
@Data
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = { "id" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "topic_id")
    private Long topicId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    public Sub(Long topicId, Long userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

}