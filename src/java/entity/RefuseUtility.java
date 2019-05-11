/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.RefuseTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tezk
 */
public class RefuseUtility {
    
    public static RefuseTO getAsTO(Refuse aRefuse) {
        RefuseTO newRefuse = new RefuseTO();
        if (aRefuse!=null) {
            // Customer responsible for the relationship
            //newRefuse.setCustomerId(aRefuse.getCustomerId());
            newRefuse.setDate(aRefuse.getDate());
        }
        return newRefuse;    
    }
    
    public static List<RefuseTO>getAsTO(List <Refuse> refuseList) {
        List <RefuseTO> newList = new ArrayList();
        for (Refuse eachRefuse : refuseList) {
            newList.add(getAsTO(eachRefuse));
        }
        return newList;
    }
    
    public static Refuse getAsEntity(RefuseTO aRefuse) {
        Refuse newRefuse = new Refuse();
        if (aRefuse!=null) {
            // Customer responsible for the relationship
            //newRefuse.setCustomerId(aRefuse.getCustomerId());
            newRefuse.setDate(aRefuse.getDate());
        }
        return newRefuse;
    }
    
    public static List<Refuse> getAsEntity(List <RefuseTO> refuseList) {
         List <Refuse> newList = new ArrayList();
        for (RefuseTO eachRefuse : refuseList) {
            newList.add(getAsEntity(eachRefuse));
        }
        return newList;       
    }


}
