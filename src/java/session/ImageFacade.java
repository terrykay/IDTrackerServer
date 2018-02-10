/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Image;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tezk
 */
@Stateless
public class ImageFacade extends AbstractFacade<Image> {

    @PersistenceContext(unitName = "IDTrackerServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void remove(Image i) {
        if (i !=null) {
            Image find = getEntityManager().find(entityClass, i.getId());   
            if (find != null)
                getEntityManager().remove(find);
        }
    }

    public ImageFacade() {
        super(Image.class);
    }
    
}
