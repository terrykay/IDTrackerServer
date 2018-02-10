/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.CaravanTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tezk
 */
public class CaravanUtility {
    public static CaravanTO getAsTO(Caravan aCaravan) {
        CaravanTO newCaravan = new CaravanTO();
        if (aCaravan == null)
            return newCaravan;
        newCaravan.setIdcaravan(aCaravan.getIdcaravan());
        newCaravan.setMake(aCaravan.getMake());
        newCaravan.setModel(aCaravan.getModel());
        newCaravan.setLength(aCaravan.getLength());
        newCaravan.setCris(aCaravan.getCris());
        if (aCaravan.getMembershipId()!=null)
            newCaravan.setMembershipId(aCaravan.getMembershipId().getId());
        
        return newCaravan;
    }
    
    public static Collection<CaravanTO> getAsTO(Collection<Caravan> caravans) {
        List<CaravanTO> newList = new ArrayList();
        for (Caravan eachCaravan : caravans) {
            newList.add(getAsTO(eachCaravan));
        }
        return newList;
    }
    
    public static Caravan getAsEntity(CaravanTO aCaravan) {
        Caravan newCaravan = new Caravan();
        if (aCaravan == null)
            return newCaravan;
        newCaravan.setIdcaravan(aCaravan.getIdcaravan());
        newCaravan.setMake(aCaravan.getMake());
        newCaravan.setModel(aCaravan.getModel());
        newCaravan.setLength(aCaravan.getLength());
        newCaravan.setCris(aCaravan.getCris());
        // Don't set membership
        return newCaravan;
    }
    
    public static Collection<Caravan> getAsEntity(Collection<CaravanTO> caravans) {
        List<Caravan> newList = new ArrayList();
        if (caravans!=null)
            for (CaravanTO eachCaravan : caravans) {
                newList.add(getAsEntity(eachCaravan));
            }
        return newList;
    }
}
