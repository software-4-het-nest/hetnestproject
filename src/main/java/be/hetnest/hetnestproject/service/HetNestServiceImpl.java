package be.hetnest.hetnestproject.service;

import be.hetnest.hetnestproject.dao.AanbiedingRepository;
import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.formdata.AanbiedingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HetNestServiceImpl implements HetNestService {

    @Autowired
    private AanbiedingRepository aanbiedingRepository;

    @Override
    public List<Aanbieding> getAanbiedingen() {
        return (List<Aanbieding>) aanbiedingRepository.findAll();
    }

    @Override
    public AanbiedingData prepareNewAanbiedingData() {

        Aanbieding lastAanbieding = aanbiedingRepository.findFirstByOrderByIdDesc();
        return prepareAanbiedingData(lastAanbieding, true);
    }

    @Override
    public AanbiedingData prepareAanbiedingData(Aanbieding theAanbieding, boolean timeShift) {

        AanbiedingData aanbiedingData = new AanbiedingData();

        if (theAanbieding != null) {
            aanbiedingData.setId(theAanbieding.getId());
            aanbiedingData.setNaam(theAanbieding.getNaam());
            aanbiedingData.setPrijs(theAanbieding.getPrijs());
            aanbiedingData.setType(theAanbieding.getType());
        } else {
            aanbiedingData.setId(0);
        }
        return aanbiedingData;
    }
}
