package com.testprojects.socnet.controller;

import static com.testprojects.socnet.controller.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testprojects.socnet.dto.PostDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test class for {@link PostController}
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@WebMvcTest(controllers = PostController.class)
public class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostController controller;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/post";

    @Test
    public void testGetLast50OrderedByLikes() throws Exception {
        mockMvc.perform(get(URL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(controller).getAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(0);
        assertThat(pageable).hasPageSize(50);
        assertThat(pageable).hasSort("likes", Sort.Direction.DESC);
    }

    @Test
    public void testGetSpecificPageOrderedByLikes() throws Exception {
        mockMvc.perform(get(URL)
                                .param("page", "1")
                                .param("size", "5")
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn();

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(controller).getAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(1);
        assertThat(pageable).hasPageSize(5);
    }

    @Test
    public void create() throws Exception {
        PostDTO dto = new PostDTO();
        dto.setAuthor("Test Name");
        dto.setText("Test Name");

        String requestBody = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
               .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        long id = 1;
        PostDTO dto = new PostDTO();
        dto.setAuthor("Test Name");
        dto.setText("Test Name");

        String requestBody = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/post/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
               .andExpect(status().isOk());
    }
}

