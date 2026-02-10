package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    private List<String> cities = new ArrayList<>(List.of(
            "Eriador", "Rohan", "Gondor", "Mordor", "Misty Mountains", "The Shire"
    ));

    private List<String> tags = new ArrayList<>(List.of(
            "Nature", "Ruins", "Historical", "Elves", "Dwarves", "Town", "Scenic", "Magic"
    ));

    public TouristRepository(){ populateRepository();}

    private void populateRepository(){
        add(new TouristAttraction("Rivendell",
                "Peaceful Elven refuge",
                "Eriador",
                List.of("Elves", "Nature", "Historical", "Town"),0));
        add(new TouristAttraction("Moria",
                "Ancient dwarven kingdoms under the mountains",
                "Misty Mountains",
                List.of("Dwarves", "Ruins", "Historical"),0));
        add(new TouristAttraction("Hobbiton",
                "Charming hobbit village with round doors and lush gardens",
                "The Shire",
                List.of("Hobbits", "Nature", "Scenic", "Town"),0));
        add(new TouristAttraction("Minas Tirith",
                "The White City built into the mountainside",
                "Gondor",
                List.of("Architecture", "Historical", "Town"),0));

        add(new TouristAttraction("Lothlórien",
                "Golden forest realm of the Galadhrim",
                "Eriador",
                List.of("Elves", "Nature"),35));

        add(new TouristAttraction("Fangorn Forest",
                "Ancient forest home to the Ents",
                "Rohan",
                List.of("Nature"),0));

        add(new TouristAttraction("Helm's Deep",
                "Fortress of the Rohirrm carved into the mountains",
                "Rohan",
                List.of("Historical"),0));
        add(new TouristAttraction(
                "Bree",
                "Bustling crossroads town where Men and Hobbits live together",
                "Eriador",
                List.of("Town"),0
        ));



    }

    public List<TouristAttraction> getAll(){ return attractions; }

    public TouristAttraction findByName(String name){
        for (TouristAttraction attraction : attractions){
            if (attraction.getName().equalsIgnoreCase(name)){
                return attraction;
            }
        }
        return null;
    }

    public void add(TouristAttraction attraction){
        attractions.add(attraction);
    }

    public void update(TouristAttraction updated){
        TouristAttraction existing = findByName(updated.getName());
        if (existing == null) {return;}

        existing.setDescription(updated.getDescription());
        existing.setCity(updated.getCity());
        existing.setTags(updated.getTags());

        existing.setTicketPrice(updated.getTicketPrice());
    }

    public void delete(String name){
        TouristAttraction found = findByName(name);
        if (found != null){ attractions.remove(found); }

        /*
        Another approach:
        for(int i = 0; i < attractions.size() ; i++) {
            if ( attractions.get(i) .getName() .equalsIgnoreCase(name) ) {
                attractions.remove(i);
                break;
            }
         }
         */
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getTags() {
        return tags;
    }

}
