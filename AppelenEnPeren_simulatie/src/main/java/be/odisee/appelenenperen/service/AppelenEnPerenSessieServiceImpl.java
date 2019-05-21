package be.odisee.appelenenperen.service;

import be.odisee.appelenenperen.dao.*;
import be.odisee.appelenenperen.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("appelenEnPerenSessieService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class AppelenEnPerenSessieServiceImpl implements AppelenEnPerenSessieService {

    private AanvraagDao aanvraagDao;

    public AppelenEnPerenSessieServiceImpl(){}

    @Autowired
    public void setAanvraagDao(AanvraagDao aanvraagDao) {
        this.aanvraagDao = aanvraagDao;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Aanvraag voegAanvraagToe(Aanvraag aanvraag) {
        
    	return aanvraagDao.saveAanvraag(aanvraag);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Aanvraag zoekAanvraagMetId(int id){
        
    	return aanvraagDao.getAanvraagById(id);
    }

    public List<Aanvraag> geefAlleAanvragen() {
        
    	return aanvraagDao.getAllAanvragen();
    }

	public void verwijderAanvraag(Aanvraag aanvraag) {
		
	}

	public void verwijderAanvraag(Integer id) {

		aanvraagDao.deleteAanvraag(zoekAanvraagMetId(id));
	}

	public void pasAanvraagAan(Aanvraag aanvraag) {
		
		aanvraagDao.updateAanvraag(aanvraag);
	}

}