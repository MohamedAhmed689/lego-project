package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.*;

@Path("/lego")
public class LegoService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("lego");	
	
	@Path("/getlego")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Lego getLego() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    Lego l=em.find(Lego.class, 1);
	    em.getTransaction().commit();		
		return l;
	}
	
	@Path("/setvalues")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LegoSetting setValues(LegoSetting ls) {
		System.out.println(ls);
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    em.merge(ls);
	    em.getTransaction().commit();		
		return ls;
	}
	@Path("/getvalues")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LegoSetting getValues() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
		Query q=em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list=q.getResultList();
		em.getTransaction().commit();		
		return list.get(0);
	}
	@Path("/getspeed")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSpeed() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
		Query q=em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list=q.getResultList();
		em.getTransaction().commit();		
		return ""+list.get(0).getSpeed();
	}
	@Path("/getdirspeed")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDirSpeed() {
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
	    List<LegoSetting> list = q.getResultList();
	    em.getTransaction().commit();
	    if (!list.isEmpty()) {
	        LegoSetting ls = list.get(0);
	        return ls.getDirection() + "#" + ls.getSpeed() + "#" + ls.getRun(); // Asumiendo que '1' es la señal para seguir corriendo
	    } else {
	        return "0#0#0"; // Asumiendo que '0#0#0' significa detener
	    }
	}

}
