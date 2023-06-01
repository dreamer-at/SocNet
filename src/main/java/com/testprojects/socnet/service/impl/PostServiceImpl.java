package com.testprojects.socnet.service.impl;

import static com.testprojects.socnet.util.validator.PostValidator.validatePost;

import java.util.ArrayList;
import java.util.List;

import com.testprojects.socnet.dto.PostDTO;
import com.testprojects.socnet.model.Post;
import com.testprojects.socnet.repository.PostRepository;
import com.testprojects.socnet.service.PostService;
import com.testprojects.socnet.util.exception.BadRequestException;
import com.testprojects.socnet.util.exception.IsAlreadyExistException;
import com.testprojects.socnet.util.exception.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Provides post service implementation
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    /**
     * Gets all posts.
     *
     * @return {@link Page} of all {@link PostDTO}s
     */
    @Override
    public Page<PostDTO> getAll(final @NonNull Pageable pageable) {
        final var posts = repository.getAllPosts(pageable);
        final var postsIsAd = processPosts(posts);
        return postsIsAd.map(PostDTO::toDto);
    }

    /**
     * Gets post by id.
     *
     * @return {@link PostDTO} by id
     * If not exists throws {@link NotFoundException}
     */
    @Override
    public PostDTO findById(final String id) throws NotFoundException {
        return repository.findById(id)
                         .map(PostDTO::toDto)
                         .orElseThrow(() -> new NotFoundException("Post", "ID", id));
    }

    /**
     * Create post.
     * Validate Author, Link, Text and Title fields and trows {@link BadRequestException}
     *
     * @return {@link PostDTO} by id
     * If not exists throws {@link NotFoundException}
     */
    @Override
    public PostDTO create(final PostDTO dto) throws BadRequestException {
        validatePost(dto);
        if (repository.existsById(dto.getId())) {
            throw new IsAlreadyExistException(Post.class, "ID", dto.getId());
        }
        log.debug("Create post with ID={}", dto.getId());
        final var post = dto.toEntity();
        return PostDTO.toDto(repository.save(post));
    }

    /**
     * Deletes post by id.
     * If not exists throws {@link NotFoundException}
     */
    @Override
    public void deleteById(final String id) throws NotFoundException {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post", "ID", id);
        }
        log.debug("Delete post with ID={}", id);
        repository.deleteById(id);
    }

    /**
     * Updates post by id.
     *
     * @return {@link PostDTO} by id
     * If not exists throws {@link NotFoundException}
     */
    @Override
    public PostDTO update(final PostDTO dto) throws NotFoundException {
        if (!repository.existsById(dto.getId())) {
            throw new NotFoundException("Post", "ID", dto.getId());
        }
        log.debug("Update post with ID={}", dto.getId());
        final var post = dto.toEntity();
        return PostDTO.toDto(repository.save(post));
    }

    /**
     * Checks post isAd.
     *
     * @return {@link PostDTO} by id
     * If not exists throws {@link NotFoundException}
     */
    @Override
    public boolean isAd(final String id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post", "ID", id);
        }
        log.debug("Get AD post status ID={}", id);
        return repository.getById(id).isAd();
    }

    /**
     * Processes requested page of post
     * Insert AD posts on 2, 15, 30 positions
     * Check if previous and next page NSFW is false
     *
     * @return {@link Page} of all {@link PostDTO}s
     */
    public Page<Post> processPosts(Page<Post> posts) {
        List<Post> allPosts = new ArrayList<>();
        List<Post> adPosts = new ArrayList<>();

        for (Post post : posts) {
            allPosts.add(post);
        }

        for (int i = 0; i < allPosts.size(); i++) {
            Post post = allPosts.get(i);
            if (post.isAd()) {
                adPosts.add(post);
                allPosts.set(i, null);
            }
        }

        int[] adPositions = {2, 15, 30};

        for (int i = 0; i < adPositions.length && i < adPosts.size(); i++) {
            int position = adPositions[i];
            if (position <= allPosts.size()) {
                allPosts.add(position - 1, adPosts.get(i));
            }
        }

        int pageSize = posts.getSize();
        int totalElements = allPosts.size();

        List<Post> pageContent = new ArrayList<>();

        for (int i = 0; i < allPosts.size(); i++) {
            Post post = allPosts.get(i);
            if (post != null) {
                if (post.isAd() && i > 0) {
                    Post prevPost = allPosts.get(i - 1);
                    if (prevPost != null && prevPost.isNSFW()) {
                        pageContent.remove(pageContent.size() - 1);
                        pageContent.add(prevPost);
                    }
                }
                pageContent.add(post);
            }
        }
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        return new PageImpl<>(pageContent, PageRequest.of(posts.getNumber(), pageSize), totalPages);
    }
}
