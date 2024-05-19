package Controller;

import Repository.HouseRepository;

import Servises.ServiseHouse;
import Servises.ServiseResudents;
import model.House;
import model.Residents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ServiseHouse houses;
    @Autowired
    private ServiseResudents residentHouse;
    @PostMapping("/addNewHouse/")
    public int addNewHouse(House house,Residents owner) {
        House house1 = houseRepository.save(house);
        houses.createHouses(house,owner);
        return house1.getHouseId();
    }

    @PostMapping("/addResidentsHouse/")
    public int addOwnerHouse(Residents residents) {
        if (residents.getResidentsId()==houseRepository.getOwnerId()) {
            // если ты хозяин дома можешь добавить жителей
            houseRepository.addResidents(residents);
            residentHouse.addResidetsHouse(residents);
        }
        return houseRepository.getCountResidents();
    }

    @GetMapping("/house/")
    public ArrayList<House> getHouse() {
        Iterable<House> houseIterable = houseRepository.findAll();
        ArrayList<House> houseArrayList = new ArrayList<>();
        for (House house : houseIterable) {
            houseArrayList.add(house);
        }
        houses.getHouses();
        return houseArrayList;
    }
    @PatchMapping("/house/{String}")
    public void updateHouse(House update, House newHouse) {
        houses.updateHouse(update,newHouse);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity getHouse(@PathVariable int id) {
        Optional<House> optional = houseRepository.findById(id);
        houses.getHouses().get(id);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity deleteHouse(@PathVariable int id) {
        houseRepository.deleteById(id);
        houses.deleteHouseId(id);
        if (!houseRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(houseRepository.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/house/")
    public ResponseEntity deleteHouse(@PathVariable House house) {
        houseRepository.delete(house);
        deleteHouse( house.getHouseId());
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/houseALL/")
    public ResponseEntity deleteHouseAll() {
        houseRepository.deleteAll();
        houses.deleteHouseAll();
        return new ResponseEntity(HttpStatus.OK);
    }

}