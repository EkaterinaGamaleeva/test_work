package Repository;

import model.House;
import model.Residents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentsRepository extends JpaRepository<Residents,
        Integer> {
    Optional<Residents> findByName(String nane);
    boolean existsByName(String name);
    boolean existsByPass(String pass);

    Optional<Object> findByName();
}
