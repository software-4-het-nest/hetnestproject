package be.hetnest.hetnestproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "INGREDIENTEN")
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty(message = "Naam mag niet leeg zijn.")
    private String naam;

    @Column
    @Min(message = "Hoeveelheid mag niet negatief zijn.", value = 0)
    private int hoeveelheid;

    @Column
    private String status; //StaatKlaar indien klaarstaat voor brouwen.

    public Ingredient(){

    }

    public Ingredient(String naam, int hoeveelheid){
        this.naam = naam;
        this.hoeveelheid = hoeveelheid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
