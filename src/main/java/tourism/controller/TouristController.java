package tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private final TouristService service;

    public TouristController(TouristService service){
        this.service = service;
    }

    // LIST ALL ATTRACTIONS
    @GetMapping
    public String listAttractions(Model model){
        model.addAttribute("attractions", service.getAll());
        return "attractionlist";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("attraction", new TouristAttraction());

        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());
        return "add-attraction";
    }

    // HANDLE ADD FORM SUBMISSION
    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        service.add(attraction);

        return "redirect:/attractions";
    }

    // SHOW EDIT FORM
    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model){
        TouristAttraction attraction = service.findByName(name);

        model.addAttribute("attraction", attraction);
        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());

        return "edit-attraction";
    }

    // HANDLE EDIT FORM SUBMISSION
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction){
        service.update(attraction);
        return "redirect:/attractions";
    }

    // DELETE ATTRACTION
    @PostMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name){
        service.delete(name);
        return "redirect:/attractions";
    }

    // SHOW TAGS PAGE
    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable String name, Model model){
        TouristAttraction attraction = service.findByName(name);

        if (attraction == null){
            throw new IllegalArgumentException("Attraction not found");
        }

        model.addAttribute("attraction", attraction);
        return "tags";
    }
}
