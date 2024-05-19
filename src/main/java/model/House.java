package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Entity
@Data
@Table(name = "house")
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "house_id")
    private int houseId;
    private String address;
    @OneToOne(mappedBy = "residents_id")
    @Column(name = "owner_id")
    private int ownerId;
    @OneToOne(mappedBy = "residents",cascade = CascadeType.ALL)
    public ArrayList<Residents> residents= new ArrayList<>();
    @Column(name = "count_residents")
    private  int countResidents;
}
