package hetnestproject_consume;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "aanvraag")
public class Aanvraag implements Serializable {


    private static final long serialVersionUID = 1L;

    private int id;
    private String vruchtensap;
    private double quantity;


    public Aanvraag() {

    }

    public Aanvraag(String vruchtensap, double quantity) {
        this.vruchtensap = vruchtensap;
        this.quantity = quantity;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    @XmlElement(name = "vruchtensap")
    public String getVruchtensap() {
        return vruchtensap;
    }

    @XmlElement(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public  void  setId(int id) {this.id = id; }

    public void setVruchtensap(String vruchtensap) {
        this.vruchtensap = vruchtensap;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


}