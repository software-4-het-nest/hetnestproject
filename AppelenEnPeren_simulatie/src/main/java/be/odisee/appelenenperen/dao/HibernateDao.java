
package be.odisee.appelenenperen.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class HibernateDao {

    SessionFactory sessionFactory;

    protected HibernateDao() {
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected void sessionSaveObject(Object o){
        try{
            sessionFactory.getCurrentSession().save(o);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Object sessionGetObjectById(String classname, int id){
        Query query = null;
        Object result = null;
        try{
            result = sessionFactory.getCurrentSession().createQuery("from "+classname+" where id="+id).uniqueResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected Object getLastObjectOfQuery(String qstr){
        Query query = null;
        Object result = null;
        try{
            List list = sessionFactory.getCurrentSession().createQuery(qstr).list();
            // als er geen objecten in het resultaat zitten, retourneren we result = null
            if (!list.isEmpty()) result = list.get(list.size()-1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected Object sessionGetObjectBy1StringParameterNamedQuery(String qstr, String parameter, String value){

        // geeft een antlr probleem .. voorlopig is er geen oplossing voor

        Query query = null;
        Object result = null;
        try{
            query = sessionFactory.getCurrentSession().createQuery(qstr);
            query.setString(parameter, value);
            result = query.uniqueResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected Object sessionGetObjectByQuery(String qstr){

        // work arround omdat vorige niet werkt

        Query query = null;
        Object result = null;
        try{
            query = sessionFactory.getCurrentSession().createQuery(qstr);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected List sessionGetAllObjects(String classname) {
        Query query = null;
        List result = null;
        try{
            query = sessionFactory.getCurrentSession().createQuery("from "+classname);
            result = query.list();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    protected void sessionUpdateObject(Object o){
    	
    	Session session = sessionFactory.getCurrentSession();
        try{
            session.update(o);
            session.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    
    protected void sessionDeleteObject(Object o){
    	
     	Session session = sessionFactory.getCurrentSession();
        try{
        	session.evict(o);
        	session.delete(session. contains(o) ? o : session.merge(o));
        	session.flush();
        }
        catch (Exception e){
        	e.printStackTrace();
        	System.out.println("Foute boel !!");
        }                          
    }


}
