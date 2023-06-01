package com.testprojects.socnet.repository;

import com.testprojects.socnet.dto.PostDTO;
import com.testprojects.socnet.model.Post;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Provides repository
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

public interface PostRepository extends BaseRepository<Post, String> {

    /**
     * Gets post by id.
     *
     * @return {@link PostDTO} by id
     */
    @NotNull Post getById(final @NotNull String id);

    /**
     * Gets a page of posts and ordered NSFW posts by true.
     * If posts are more than 3, the query inserts AD posts to the page.
     *
     * @return {@link Page<PostDTO>}
     */
    @Query("SELECT p FROM Post p WHERE p.isAd = false OR (p.isAd = true AND (SELECT COUNT(p2) FROM Post p2 WHERE p2" +
            ".isAd = false) > 3) ORDER BY CASE WHEN p.NSFW = true THEN 1 ELSE 0 END ASC")
    Page<Post> getAllPosts(final @NonNull Pageable pageable);
}
