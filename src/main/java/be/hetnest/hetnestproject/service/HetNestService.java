package be.hetnest.hetnestproject.service;


import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.formdata.AanbiedingData;

import java.util.List;

public interface HetNestService {
    List<Aanbieding> getAanbiedingen();

    AanbiedingData prepareNewAanbiedingData();

    AanbiedingData prepareAanbiedingData(Aanbieding theAanbieding, boolean timeShift);
}
