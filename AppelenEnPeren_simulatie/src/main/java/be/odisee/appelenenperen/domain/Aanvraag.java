package be.odisee.appelenenperen.domain;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

import javax.persistence.*;
import org.hibernate.annotations.Index;

import javax.xml.bind.annotation.*;

@Entity
@Table(name="aanvragen")
@XmlRootElement(name="aanvraag")
public class Aanvraag implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private String vruchtensap;

    @Column
    private double quantity;


    public Aanvraag() {

    }

    public Aanvraag(String vruchtensap, double quantity) {
        this.vruchtensap = vruchtensap;
        this.quantity = quantity;
    }

    public Aanvraag(int id, String vruchtensap, double quantity) {
        this.id = id;
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
