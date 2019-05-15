package be.hetnest.hetnestproject.service;

import be.hetnest.hetnestproject.dao.AanbiedingRepository;
import be.hetnest.hetnestproject.dao.AanvragenRepository;
import be.hetnest.hetnestproject.dao.IngredientenRepository;
import be.hetnest.hetnestproject.dao.UserRepository;
import be.hetnest.hetnestproject.domain.Aanbieding;
import be.hetnest.hetnestproject.domain.Aanvraag;
import be.hetnest.hetnestproject.domain.Ingredient;
import be.hetnest.hetnestproject.domain.User;
import be.hetnest.hetnestproject.formdata.AanbiedingData;
import lombok.extern.slf4j.Slf4j;
import be.hetnest.hetnestproject.service.HetNestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AanvragenRepository aanvragenRepository;

    @Autowired
    private IngredientenRepository ingredientenRepository;

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
    public Aanbieding addAanbieding(int hoeveelheid, String status, double prijs, String type, String naam){
        return this.aanbiedingRepository.save(new Aanbieding(hoeveelheid, status, prijs, type, naam));
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
    public List<Aanbieding> getAllAanbiedingenByStatus(String status) {
        return (aanbiedingRepository.findAllByStatus(status));
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

    @Override
    public List<Aanvraag> getAanvragen() { return this.aanvragenRepository.findAll();}

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Aanbieding voegAanbiedingToeREST(Aanbieding aanbieding) {

        return aanbiedingRepository.save(aanbieding);
    }

    private String getAuthenticatedUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    private User findAuthenticatedUser() {

        String username = getAuthenticatedUsername();
        return userRepository.findByUsername(username);
    }

    @Override
    public String getAuthenticatedRole() {

        User theUser = findAuthenticatedUser();
        return theUser.getRole();
    }

    @Override
    public List<Ingredient> getIngredienten() { return this.ingredientenRepository.findAll();}

    @Override
    public Ingredient getIngredientById(long id){
        return this.ingredientenRepository.findById(id);
    }

    @Override
    public List<Ingredient> getAllIngredientenByStatus(String status) {
        return (ingredientenRepository.findAllByStatus(status));
    }

    @Override
    public void saveIngredient(Ingredient ingredient){
        ingredientenRepository.save(ingredient);
    }
}
