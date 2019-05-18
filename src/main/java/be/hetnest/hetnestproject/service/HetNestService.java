package be.hetnest.hetnestproject.service;


import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.domain.Ingredient;
import be.hetnest.hetnestproject.domain.Brouwsel;
import be.hetnest.hetnestproject.formdata.AanbiedingData;

import java.util.List;

public interface HetNestService {

    String getAuthenticatedRole();
    List<Aanbieding> getAanbiedingen();
    List<Aanbieding> getAllAanbiedingenByStatus(String status);
    AanbiedingData prepareNewAanbiedingData();

    AanbiedingData prepareAanbiedingData(Aanbieding theAanbieding, boolean timeShift);
    Aanbieding addAanbieding(int hoeveelheid, String status, double prijs, String type, String naam);
    Aanbieding voegAanbiedingToeREST(Aanbieding aanbieding);
    Aanbieding getAanbiedingById (long id);
    Aanbieding getAanbiedingByName (String naam);
    Aanbieding getAanbiedingByBrouwsel (Brouwsel brouwsel);
    List<Aanbieding> getAlleAanbiedingen();
    List<Aanbieding> getAlleAanbiedingenByBrouwsel(Brouwsel brouwsel);
    void updateAanbieding(Aanbieding aanbieding);
    void deleteAanbieding (Aanbieding aanbieding);
    void deleteAanbiedingById (long id);

    List<Aanvraag> getAanvragen();

    Ingredient getIngredientById(long id);
    List<Ingredient> getIngredienten();
    List<Ingredient> getAllIngredientenByStatus(String status);
    void saveIngredient(Ingredient ingredient);

    Brouwsel getBrouwselById(long id);
    Brouwsel addBrouwsel(String naam, String naamExterneBrouwer);
    List<Brouwsel> getAlleBrouwsels();
    void updateBrouwsel(Brouwsel brouwsel);
    void deleteBrouwsel(Brouwsel brouwsel);
    void deleteBrouwselById(long id);



}
