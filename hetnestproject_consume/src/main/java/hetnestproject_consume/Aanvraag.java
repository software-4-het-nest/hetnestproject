package hetnestproject_consume;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name = "aanvraag")
public class Aanvraag implements Serializable {


    private long id;

    private int hoeveelheid;

    private double prijs;

    private String type;

    private String naam;

    public Aanvraag()
    {

    }
    public Aanvraag(int _hoeveelheid, double _prijs, String _type, String _naam)
    {
        this.hoeveelheid = _hoeveelheid;
        this.prijs = _prijs;
        this.type = _type;
        this.naam = _naam;
    }

    @XmlElement(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long _id)
    {
        this.id =_id;
    }

    @XmlElement(name = "hoeveelheid")
    public int getHoeveelheid()
    {
        return this.hoeveelheid;
    }

    @XmlElement(name = "prijs")
    public double getPrijs()
    {
        return this.prijs;
    }

    @XmlElement(name = "type")
    public String getType()
    {
        return this.type;
    }

    @XmlElement(name = "naam")
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
