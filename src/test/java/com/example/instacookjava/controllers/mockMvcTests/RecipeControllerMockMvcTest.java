package com.example.instacookjava.controllers.mockMvcTests;
import com.example.instacookjava.controllers.RecipeController;
import com.example.instacookjava.models.Recipe;
import com.example.instacookjava.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RecipeControllerMockMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private Model model;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RecipeController(recipeService), model).build();
    }

    @Test
    @WithMockUser(username = "claudia.ursu@yahoo.com", password = "parola123", roles = "ADMIN")
    public void getByIdMockMvc() throws Exception {
        Integer id = 1;
        Recipe recipeTest = new Recipe();
        recipeTest.setRecipeId(id);
        recipeTest.setRecipeTitle("test");

        when(recipeService.getRecipeById(id)).thenReturn(recipeTest);

        mockMvc.perform(get("/recipes/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/recipeDetails"))
                .andExpect(model().attribute("recipe", recipeTest));
    }
}


