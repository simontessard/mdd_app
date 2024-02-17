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
    private Integer id;

    @NotNull
    @Column(name = "topic_id")
    private Integer topicId;

    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    public Sub(Integer topicId, Integer userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

}