package be.odisee.appelenenperen.service;

import be.odisee.appelenenperen.domain.*;

import java.util.List;

public interface AppelenEnPerenSessieService {

    public Aanvraag voegAanvraagToe(Aanvraag aanvraag);

    public Aanvraag zoekAanvraagMetId(int id);

    public List<Aanvraag> geefAlleAanvragen();

	public void verwijderAanvraag(Integer id);

	public void pasAanvraagAan(Aanvraag aanvraag);

}