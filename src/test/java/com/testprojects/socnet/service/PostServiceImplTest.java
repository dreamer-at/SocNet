package com.testprojects.socnet.service;

import static com.testprojects.socnet.util.Utils.generateUniqueId;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.testprojects.socnet.dto.Category;
import com.testprojects.socnet.dto.PostDTO;
import com.testprojects.socnet.model.Post;
import com.testprojects.socnet.repository.PostRepository;
import com.testprojects.socnet.service.impl.PostServiceImpl;
import com.testprojects.socnet.util.exception.BadRequestException;
import com.testprojects.socnet.util.exception.IsAlreadyExistException;
import com.testprojects.socnet.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Integration test class for {@link PostServiceImpl}
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl unit;

    @Mock
    PostRepository repository;

    @Test
    public void create_nominal() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e4682137")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        when(repository.existsById(post.getId())).thenReturn(false);
        when(repository.save(any(Post.class))).thenReturn(post);

        final var result = unit.create(dto);

        //THEN
        assertEquals(dto, result);
    }

    @Test
    public void create_author_is_not_valid() {
        //GIVEN
        final var post = Post.builder()
                             .id(generateUniqueId())
                             .title("title1")
                             .author("auTHor1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("https://socnet.com/link1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();
        final var dto = PostDTO.toDto(post);

        //WHEN
        Exception exception = assertThrows(BadRequestException.class, () -> {
            unit.create(dto);
        });

        //THEN
        assertEquals("Bad request in resource:'Post' for field:'Author' with value:'auTHor1'",
                     exception.getMessage());
    }

    @Test
    public void create_link_is_not_valid() {
        //GIVEN
        final var post = Post.builder()
                             .id(generateUniqueId())
                             .title("title1")
                             .author("author")
                             .link("https:_socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("https://socnet.com/link1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        Exception exception = assertThrows(BadRequestException.class, () -> unit.create(dto));

        //THEN
        assertEquals("Bad request in resource:'Post' for field:'Link' with value:'https:_socnet.com/link1'",
                     exception.getMessage());
    }

    @Test
    public void create_text_is_not_valid() {
        //GIVEN
        final var post = Post.builder()
                             .id(generateUniqueId())
                             .title("title1")
                             .author("author")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("Hi all, this is my link https://socnet.com/link1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        Exception exception = assertThrows(BadRequestException.class, () -> unit.create(dto));

        //THEN
        assertEquals("Bad request in resource:'Post' for field:'Text' with value:" +
                             "'Hi all, this is my link https://socnet.com/link1'",
                     exception.getMessage());
    }

    @Test
    public void create_is_already_exist_exception() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301985a14934647547e98126d24ba2a7e866")
                             .title("title1")
                             .author("author")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("Test1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        when(repository.existsById(post.getId())).thenReturn(true);
        Exception exception = assertThrows(IsAlreadyExistException.class, () -> unit.create(dto));

        //THEN
        assertEquals("Post with ID:'202305301985a14934647547e98126d24ba2a7e866' is already exist",
                     exception.getMessage());
    }

    @Test
    public void findById() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e4682137")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        when(repository.findById(post.getId())).thenReturn(Optional.of(post));

        final var result = unit.findById(dto.getId());

        //THEN
        assertEquals(dto, result);
    }

    @Test
    public void update() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e4682137")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        when(repository.existsById(post.getId())).thenReturn(true);
        when(repository.save(any(Post.class))).thenReturn(post);

        final var result = unit.update(dto);

        //THEN
        assertEquals(dto, result);
    }

    @Test
    public void update_is_already_exist_exception() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e4682137")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var dto = PostDTO.toDto(post);

        //WHEN
        when(repository.existsById(post.getId())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> unit.update(dto));

        //THEN
        assertEquals("Post not found with ID=202305301780c3ca2d37d94e90a621a239e4682137",
                     exception.getMessage());
    }

    @Test
    public void delete() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e4682137")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(false)
                             .NSFW(false)
                             .build();

        final var postDeleted = Post.builder()
                                    .id("202305301780c3ca2d37d94e90a621a239e4682137")
                                    .title("title1")
                                    .author("author1")
                                    .link("https://socnet.com/link1")
                                    .category(Category.HISTORY)
                                    .text("text1")
                                    .likes(200)
                                    .dislikes(50)
                                    .isAd(false)
                                    .NSFW(false)
                                    .isEnabled(false)
                                    .build();


        //WHEN
        when(repository.existsById(post.getId())).thenReturn(true);

        unit.deleteById(post.getId());

        //THEN
        Assertions.assertNotEquals(post, postDeleted);
    }

    @Test
    public void isAd() {
        //GIVEN
        final var post = Post.builder()
                             .id("202305301780c3ca2d37d94e90a621a239e47")
                             .title("title1")
                             .author("author1")
                             .link("https://socnet.com/link1")
                             .category(Category.HISTORY)
                             .text("text1")
                             .likes(200)
                             .dislikes(50)
                             .isAd(true)
                             .NSFW(false)
                             .build();

        //WHEN
        when(repository.existsById(post.getId())).thenReturn(true);
        when(repository.getById(any(String.class))).thenReturn(post);

        final var result = unit.isAd(post.getId());

        //THEN
        verify(repository, times(1)).getById(String.valueOf(post.getId()));
        Assertions.assertTrue(result);
    }
}
