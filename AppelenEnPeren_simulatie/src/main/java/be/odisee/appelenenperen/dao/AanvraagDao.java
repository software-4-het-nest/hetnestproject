package be.odisee.appelenenperen.dao;

import be.odisee.appelenenperen.domain.*;

import java.util.List;

public interface AanvraagDao {

    public Aanvraag saveAanvraag(Aanvraag aanvraag);

    public Aanvraag getAanvraagById(int aanvraagId);

    public List<Aanvraag> getAllAanvragen();

    public void updateAanvraag(Aanvraag aanvraag);

	public void deleteAanvraag(Aanvraag aanvraag);

}
