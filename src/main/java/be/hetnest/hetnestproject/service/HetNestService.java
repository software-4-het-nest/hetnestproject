package be.hetnest.hetnestproject.service;


import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.formdata.AanbiedingData;

import java.util.List;

public interface HetNestService {
    List<Aanbieding> getAanbiedingen();

    AanbiedingData prepareNewAanbiedingData();

    AanbiedingData prepareAanbiedingData(Aanbieding theAanbieding, boolean timeShift);
    
    Aanbieding addAanbieding(int _hoeveelheid, double _prijs, String _type, String _naam);
    Aanbieding voegAanbiedingToeREST(Aanbieding aanbieding);
    Aanbieding getAanbiedingById (long id);
    Aanbieding getAanbiedingByName (String naam);
    List<Aanbieding> getAlleAanbiedingen();
    void updateAanbieding(Aanbieding aanbieding);
    void deleteAanbieding (Aanbieding aanbieding);
    void deleteAanbiedingById (long id);

    List<Aanvraag> getAanvragen();
}
