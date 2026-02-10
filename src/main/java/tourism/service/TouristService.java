package tourism.service;

import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository){
        this.repository = repository;
    }

    public List<TouristAttraction> getAll(){ return  repository.getAll();}

    public TouristAttraction findByName(String name){

        TouristAttraction found = repository.findByName(name);
        if (found == null){
            throw new IllegalArgumentException("Attraction not found");
        }
        return found;
    }

    public void add(TouristAttraction attraction){
        repository.add(attraction);
    }

    public void update(TouristAttraction attraction){
        repository.update(attraction);
    }

    public void delete(String name){
        repository.delete(name);
    }

    public List<String> getCities(){
        return repository.getCities();
    }

    public List<String> getTags(){
        return repository.getTags();
    }
}
