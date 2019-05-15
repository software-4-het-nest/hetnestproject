package be.hetnest.hetnestproject.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "BROUWSELS")
@Data
public class Brouwsel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String naam;

    @Column
    private String naamExterneBrouwer;


    /**private List<Ingredient> brouwselToegevoegdeIngrediënten;**/
}
