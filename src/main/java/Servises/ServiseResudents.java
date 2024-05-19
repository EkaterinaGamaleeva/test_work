package Servises;

import Repository.ResidentsRepository;
import model.Residents;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiseResudents {

    private static JwtServis JwtService;

    private static ArrayList<Residents> residentsHouse;

    private int countResidents;
    private ResidentsRepository residentsRepository;



    public int getCountResidents() {
        this.countResidents=residentsHouse.size();
        return countResidents;
    }

    public void createHouses(Residents residents) {
        residentsHouse.add(residents);

    }


    public void addResidetsHouse(Residents residents){

        residentsHouse.add(residents);
    }


    public void deleteResidentsHouseId(int index){
        residentsHouse.remove(index);
    }
    public void deleteResidentsHouseAll(){
        residentsHouse.clear();
    }

    public void updateResidentsHouse(Residents update , Residents newResidents) {

        for (int i = 0; i < residentsHouse.size(); i++) {
            if (update == residentsHouse.get(i)) {
                deleteResidentsHouseId(i);
                residentsHouse.add(i, newResidents);
            }
        }
    }


    public Residents getByUsername(String name) {
        return residentsRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким именем не найден"));

    }
    public ArrayList<Residents> getResidentsHouse() {
        return residentsHouse;
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
