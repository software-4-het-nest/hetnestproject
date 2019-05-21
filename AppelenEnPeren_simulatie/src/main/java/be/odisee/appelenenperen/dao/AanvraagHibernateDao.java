package be.odisee.appelenenperen.dao;

import be.odisee.appelenenperen.domain.Aanvraag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("aanvraagDao")
public class AanvraagHibernateDao extends HibernateDao implements AanvraagDao {

    public Aanvraag saveAanvraag(Aanvraag aanvraag) {
        sessionSaveObject(aanvraag);
        return aanvraag;
    }

    public Aanvraag getAanvraagById(int aanvraagId) {
        return (Aanvraag) sessionGetObjectById("Aanvraag", aanvraagId);
    }

    public List<Aanvraag> getAllAanvragen() {
        return (List<Aanvraag>) sessionGetAllObjects("Aanvraag");
    }

    public void updateAanvraag(Aanvraag aanvraag) {
        sessionUpdateObject(aanvraag);
    }

	public void deleteAanvraag(Aanvraag aanvraag) {
		sessionDeleteObject(aanvraag);
	}

}
