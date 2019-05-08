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
    private final long id;

    @Column
    private int hoeveelheid;

    @Column
    private double prijs;

    @Column
    private String type;

    @Column
    private String naam;

}
