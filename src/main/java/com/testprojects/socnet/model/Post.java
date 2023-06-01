package com.testprojects.socnet.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.testprojects.socnet.dto.Category;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.Nullable;

/**
 * Post entity
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "post")
@DynamicUpdate
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Post extends BaseEntity {

    @Id
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String link;

    @NotNull
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Category category;

    @NotNull
    private String text;

    private int likes;

    private int dislikes;

    private boolean isAd;

    private boolean NSFW;

    @Nullable
    @Builder.Default
    private Boolean isEnabled = true;
}
