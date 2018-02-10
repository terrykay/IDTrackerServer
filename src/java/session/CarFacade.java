/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tezk
 */
@Stateless
public class CarFacade extends AbstractFacade<Car> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void remove(Car c) {
        Integer idcar = c.getIdcar();
        if (idcar != null) {
            Car find = getEntityManager().find(entityClass, idcar);
            if (find != null)
                getEntityManager().remove(find);
        }
    }

    public CarFacade() {
        super(Car.class);
    }
    
}
