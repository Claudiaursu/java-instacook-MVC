package com.example.instacookjava.controllers.mockMvcTests;
import com.example.instacookjava.controllers.CollectionController;
import com.example.instacookjava.models.Collection;
import com.example.instacookjava.models.Recipe;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionControllerMockMvcTest {

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
    public void getByIdMockMvc() throws Exception {
        Integer id = 1;
        Collection collectionTest = new Collection();
        collectionTest.setCollectionId(id);
        collectionTest.setCollectionTitle("test");

        when(collectionService.getCollectionById(id)).thenReturn(collectionTest);

        mockMvc.perform(get("/collections/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("collections/collectionDetails"))
                .andExpect(model().attribute("collection", collectionTest));
    }
}


