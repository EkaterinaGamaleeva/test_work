package Repository;

import model.House;
import model.Residents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
    ArrayList<Residents> addResidents(Residents residents);
    Integer getCountResidents();
    Integer getOwnerId();

}
