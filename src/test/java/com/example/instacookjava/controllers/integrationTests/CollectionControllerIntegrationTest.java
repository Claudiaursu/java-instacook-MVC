package com.example.instacookjava.controllers.integrationTests;
import com.example.instacookjava.controllers.CollectionController;
import com.example.instacookjava.controllers.RecipeController;
import com.example.instacookjava.services.CollectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionControllerIntegrationTest {

    private MockMvc mockMvc;

    @MockBean
    private CollectionService collectionService;

    @MockBean
    private Model model;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CollectionController(collectionService), model).build();
    }

    @Test
    @WithMockUser(username = "claudia.ursu@yahoo.com", password = "parola123", roles = "ADMIN")
    public void showByIdMvc() throws Exception {

        mockMvc.perform(get("/collections"))
                .andExpect(status().isOk())
                .andExpect(view().name("collections/collectionList"));
    }

    @Test
    @WithMockUser(username = "claudia.ursu@yahoo.com", password = "parola123", roles = "GUEST")
    public void showByIdNotFound() throws Exception {

        mockMvc.perform(get("/collections/{id}", "17"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("fragments/notFound"));
    }
}
