package tourism.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService service;

    @Test
    void shouldListAllAttractions() throws Exception {
        List<TouristAttraction> mockList = List.of(
                new TouristAttraction(
                        "Rivendell",
                        "Peaceful refuge",
                        "Eriador",
                        List.of("Elves"),
                        0
                ));

        when(service.getAll()).thenReturn(mockList);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionlist"))
                .andExpect(model().attribute("attractions", mockList));
    }

    @Test
    void showAddForm() throws Exception {
        when(service.getCities()).thenReturn(List.of("Gondor"));
        when(service.getTags()).thenReturn(List.of("Historical"));

        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-attraction"))
                .andExpect(model().attributeExists("attraction"))
                .andExpect(model().attribute("cities", List.of("Gondor")))
                .andExpect(model().attribute("tags", List.of("Historical")));

    }
}
