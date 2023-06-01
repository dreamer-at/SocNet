package com.testprojects.socnet.service;

import com.testprojects.socnet.dto.PostDTO;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Provides post service
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

public interface PostService {

    /**
     * Gets all posts.
     *
     * @return {@link Page} of all {@link PostDTO}s
     */
    Page<PostDTO> getAll(final @NonNull Pageable page);

    /**
     * Gets post by id.
     *
     * @return {@link PostDTO} by id
     */
    PostDTO findById(final String id);

    /**
     * Create post.
     *
     * @return {@link PostDTO} by id
     */
    PostDTO create(final PostDTO dto);

    /**
     * Deletes post by id.
     *
     */
    void deleteById(final String id);

    /**
     * Updates post by id.
     *
     * @return {@link PostDTO} by id
     */
    PostDTO update(final PostDTO dto);

    /**
     * Checks post isAd.
     *
     * @return {@link PostDTO} by id
     */
    boolean isAd(final String id);
}
