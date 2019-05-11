package be.hetnest.hetnestproject.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AANBIEDINGEN")
@Data
@RequiredArgsConstructor
public class Aanbieding {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private int hoeveelheid;

    @Column
    private double prijs;

    @Column
    private String type;

    @Column
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
