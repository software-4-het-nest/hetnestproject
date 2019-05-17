package be.hetnest.hetnestproject.domain;


import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "BROUWSELS")
@Data
public class Brouwsel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty
    private String naam;

    @Column
    private String naamExterneBrouwer;

    @OneToMany(mappedBy = "brouwsel")
    private List<Aanbieding> klaargezetteIngredienten;


    public Brouwsel()
    {

    }

    public Brouwsel(String naam, String naamExterneBrouwer, @Nullable List<Aanbieding> klaargezetteIngredienten)
    {
        this.naam = naam;
        this.naamExterneBrouwer = naamExterneBrouwer;
        klaargezetteIngredienten = new ArrayList<>();
    }

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public String getNaam() { return this.naam; }
    public void setNaam(String naam) { this.naam = naam; }

    public String getNaamExterneBrouwer() { return naamExterneBrouwer; }
    public void setNaamExterneBrouwer(String naamExterneBrouwer) { this.naamExterneBrouwer = naamExterneBrouwer; }


}
