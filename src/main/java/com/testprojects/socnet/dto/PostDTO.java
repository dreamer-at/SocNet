package com.testprojects.socnet.dto;

import static com.testprojects.socnet.util.Utils.generateUniqueId;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.testprojects.socnet.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Provides dto for post entity
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {

    private String id;
    private String title;
    private String author;
    private String link;
    private Category category;
    private String text;
    private int likes;
    private int dislikes;
    private boolean isAd;
    private boolean NSFW;

    public static PostDTO toDto(final Post post) {
        Objects.requireNonNull(post);

        return PostDTO.builder()
                      .id(post.getId())
                      .title(post.getTitle())
                      .author(post.getAuthor())
                      .link(post.getLink())
                      .category(post.getCategory())
                      .text(post.getText())
                      .likes(post.getLikes())
                      .dislikes(post.getDislikes())
                      .isAd(post.isAd())
                      .NSFW(post.isNSFW())
                      .build();
    }

    public Post toEntity() {
        return Post.builder()
                   .id(generateUniqueId())
                   .title(title)
                   .author(author)
                   .link(link)
                   .category(category)
                   .text(text)
                   .likes(likes)
                   .dislikes(dislikes)
                   .isAd(isAd)
                   .NSFW(NSFW)
                   .build();
    }
}
