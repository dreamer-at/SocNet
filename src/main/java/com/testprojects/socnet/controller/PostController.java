package com.testprojects.socnet.controller;

import javax.validation.Valid;

import com.testprojects.socnet.dto.PostDTO;
import com.testprojects.socnet.service.PostService;
import com.testprojects.socnet.util.ApiPageable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Provides endpoints for post
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    /**
     * Gets all posts.
     *
     * @return {@link Page} of all {@link PostDTO}s
     */
    @ApiPageable
    @GetMapping
    ResponseEntity<Page<PostDTO>> getAll(@PageableDefault(sort = {"likes"}, size = 50,
                                    direction = Sort.Direction.DESC)
                                    final @NonNull Pageable pageable) {
        final var posts = service.getAll(pageable);
        return ResponseEntity.ok(posts);
    }

    /**
     * Gets post by id.
     *
     * @return {@link PostDTO} by id
     */
    @GetMapping(value = "/{id}")
    ResponseEntity<?> getById(@NonNull @PathVariable("id") final String id) {
        final var post = service.findById(id);
        return ResponseEntity.ok(post);
    }

    /**
     * Gets post by id.
     *
     * @return {@link PostDTO} by id
     */
    @PostMapping
    ResponseEntity<?> create(@NonNull final PostDTO dto) {
        final var post = service.create(dto);
        return ResponseEntity.ok(post);
    }

    /**
     * Updates post.
     *
     * @return {@link PostDTO} by id
     */
    @PutMapping(value = "/{id}")
    ResponseEntity<?> update(@RequestBody @Valid final PostDTO dto) {
        final var post = service.update(dto);
        return ResponseEntity.ok(post);
    }

    /**
     * Deletes post by id.
     */
    @DeleteMapping("{/id}")
    ResponseEntity<?> delete(@NonNull @PathVariable("id") final String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
