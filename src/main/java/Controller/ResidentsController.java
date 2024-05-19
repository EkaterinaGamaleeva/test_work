package Controller;

import Repository.ResidentsRepository;
import Servises.ServiseHouse;
import Servises.ServiseResudents;
import model.Residents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ResidentsController {
    @Autowired
    private ResidentsRepository residentsRepository;
    @Autowired
    private ServiseHouse houses;
    @Autowired
    private ServiseResudents residentHouse;

    @PostMapping("/residents/")
    public int addNewResident(Residents residents) {
        Residents residents1 = residentsRepository.save(residents);
        residentHouse.addResidetsHouse(residents);
        return residents1.getResidentsId();
    }

    @GetMapping("/residents/")
    public ArrayList<Residents> getResidents() {
        Iterable<Residents> residentsIterable =residentsRepository.findAll();
        ArrayList<Residents> residentsArrayList = new ArrayList<>();
        for (Residents residents : residentsIterable) {
            residentsArrayList.add(residents);
        }
        residentHouse.getResidentsHouse();
        return residentsArrayList;
    }
    @PatchMapping("/resident/{String}")
    public void updateResident(Residents update, Residents newResidets) {

        residentHouse.updateResidentsHouse(update,newResidets);
    }

    @GetMapping("/resident/{id}")
    public ResponseEntity getResident(@PathVariable int id) {
        Optional<Residents> optional = residentsRepository.findById(id);
        residentHouse.getResidentsHouse().get(id);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/residents/{id}")
    public ResponseEntity deleteHouse(@PathVariable int id) {
        residentHouse.deleteResidentsHouseId(id);
        residentsRepository.deleteById(id);
        if (!residentsRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(residentsRepository.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/residents/")
    public ResponseEntity deleteResidents(@PathVariable Residents residents) {
        residentsRepository.delete(residents);
        residentHouse.deleteResidentsHouseId(residents.getResidentsId());
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/residents/")
    public ResponseEntity deleteResidentsAll() {
        residentsRepository.deleteAll();
        residentHouse.deleteResidentsHouseAll();
        return new ResponseEntity(HttpStatus.OK);
    }

}
