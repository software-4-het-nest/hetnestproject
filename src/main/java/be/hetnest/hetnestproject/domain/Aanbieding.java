package be.hetnest.hetnestproject.domain;

import lombok.Data;

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
    @NotEmpty(message = "De status mag niet leeg zijn.")
    private String status;

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

    @ManyToOne
    @JoinColumn(name = "brouwsel")
    private Brouwsel brouwsel;

    @Column
    private String extra;

    
    public Aanbieding()
    {
    	
    }
    public Aanbieding(int hoeveelheid, String status, double prijs, String type, String naam)
    {
        this.status = status;
    	this.hoeveelheid = hoeveelheid;
    	this.prijs = prijs;
    	this.type = type;
    	this.naam = naam;
    }

    public String getExtra() { return this.extra; }

    public void setExtra(String extra) { this.extra = extra; }

    public String getStatus() { return this.status;}

    public void setStatus(String status) { this.status = status; }

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
    
    public void setHoeveelheid(int hoeveelheid)
    {
        this.hoeveelheid = hoeveelheid;
    }

    public void setPrijs(double prijs)
    {
        this.prijs = prijs;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setNaam(String naam){
        this.naam = naam;
    }

    public void setBrouwsel(Brouwsel brouwsel) { this.brouwsel = brouwsel; }

    public Brouwsel getBrouwsel() { return this.brouwsel; }

}
