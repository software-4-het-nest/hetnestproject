package be.hetnest.hetnestproject.service;

import be.hetnest.hetnestproject.dao.AanbiedingRepository;
import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.formdata.AanbiedingData;
import lombok.extern.slf4j.Slf4j;
import be.hetnest.hetnestproject.service.HetNestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("HetNestService")
@Transactional
public class HetNestServiceImpl implements HetNestService {

    @Autowired
    private AanbiedingRepository aanbiedingRepository;
    
    public HetNestServiceImpl(){}

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
            /**aanbiedingData.setId(theAanbieding.getId());
            aanbiedingData.setNaam(theAanbieding.getNaam());
            aanbiedingData.setPrijs(theAanbieding.getPrijs());
            aanbiedingData.setType(theAanbieding.getType());**/
        } else {
            /**aanbiedingData.setId(0);**/
        }
        return aanbiedingData;
    }
    
    @Override
    public Aanbieding addAanbieding(int _hoeveelheid, double _prijs, String _type, String _naam){
        return this.aanbiedingRepository.save(new Aanbieding(_hoeveelheid, _prijs, _type, _naam));
    }

    @Override
    public Aanbieding getAanbiedingById(long id){
        return this.aanbiedingRepository.findById(id);
    }

    @Override
    public Aanbieding getAanbiedingByName(String naam){
        return this.aanbiedingRepository.findByNaam(naam);
    }

    @Override
    public List<Aanbieding> getAlleAanbiedingen(){
        return this.aanbiedingRepository.findAll();
    }

    @Override
    public void updateAanbieding(Aanbieding aanbieding){
        this.aanbiedingRepository.save(aanbieding);
    }

    @Override
    public void deleteAanbieding(Aanbieding aanbieding){
        this.aanbiedingRepository.delete(aanbieding);
    }

    @Override
    public void deleteAanbiedingById(long id){
        this.aanbiedingRepository.deleteById(id);
    }
}
