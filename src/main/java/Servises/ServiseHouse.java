package Servises;

import DTO.RegistrationResidents;
import DTO.ResponseAutentification;
import model.House;
import model.Residents;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiseHouse {

    private static ArrayList<House> houses;


    private static ArrayList<Residents> residentsHouse;

    private Residents owner;
    private int ownerId;
    private int countResidents;



    public int getCountResidents() {
        this.countResidents=residentsHouse.size();
        return countResidents;
    }
    public void createHouses(House house, Residents residents) {
        residentsHouse.add(residents);
        this.ownerId=residents.getResidentsId();
        this.owner=residents;
        this.houses = new ArrayList<>();
        houses.add(house);
    }


    public  void deleteHouseId(int index){
        houses.remove(index);
    }
    public void deleteHouseAll(){
        houses.clear();
    }
    public void updateHouse(House update , House newHouse) {
        for (int i = 0; i < houses.size(); i++) {
            if (update == houses.get(i)) {
                deleteHouseId(i);
                houses.add(i, newHouse);
            }
        }
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public ArrayList<Residents> getResidentsHouse() {
        return residentsHouse;
    }

    public Residents getOwner() {
        return owner;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
