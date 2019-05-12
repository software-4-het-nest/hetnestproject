package be.hetnest.hetnestproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "AANBIEDINGEN")
@Data
public class Aanbieding {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @Min(message = "Hoeveelheid mag niet negatief zijn.", value = 0)
    private int hoeveelheid;

    @Column
    @Min(message = "Prijs mag niet negatief zijn.", value = 0)
    private double prijs;

    @Column
    @NotEmpty(message = "Type mag niet leeg zijn.")
    private String type;

    @Column
    @NotEmpty(message = "Naam mag niet leeg zijn.")
    private String naam;
    
    public Aanbieding()
    {
    	
    }
    public Aanbieding(int _hoeveelheid, double _prijs, String _type, String _naam)
    {
    	this.hoeveelheid = _hoeveelheid;
    	this.prijs = _prijs;
    	this.type = _type;
    	this.naam = _naam;
    }
    
    public long getId() {
    	return this.id;
    }
    
    public void setId(long _id)
    {
    	this.id =_id;
    }
    
    public int getHoeveelheid()
    {
    	return this.hoeveelheid;
    }
    
    public double getPrijs()
    {
    	return this.prijs;
    }
    
    public String getType()
    {
    	return this.type;
    }
    
    public String getNaam()
    {
    	return this.naam;
    }
    
    public void setHoeveelheid(int _hoeveelheid)
    {
        this.hoeveelheid = _hoeveelheid;
    }

    public void setPrijs(double _prijs)
    {
        this.prijs = _prijs;
    }

    public void setType(String _type)
    {
        this.type = _type;
    }


    public void setNaam(String naam){
        this.naam = naam;
    }

}
