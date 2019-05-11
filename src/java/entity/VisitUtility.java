/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.VisitTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tezk
 */
public class VisitUtility {
    
    public static VisitTO getAsTO(Visit aVisit) {
        VisitTO newVisit = new VisitTO();
        if (aVisit==null) 
            return newVisit;

        newVisit.setId(aVisit.getIdvisit());
        newVisit.setStartDate(aVisit.getStartDate());
        newVisit.setEndDate(aVisit.getEndDate());
        newVisit.setUnitId(UnitUtility.getAsTO(aVisit.getUnitId()));
        newVisit.setType(aVisit.getType());

        return newVisit;    
    }
    
    public static List<VisitTO>getAsTO(List <Visit> visitList) {
        List <VisitTO> newList = new ArrayList();
        for (Visit eachVisit : visitList) {
            newList.add(getAsTO(eachVisit));
        }
        return newList;
    }
 
        
    public static Visit getAsEntity(VisitTO aVisit) {
        
        System.out.println("VisitTO : "+aVisit.getId());
        System.out.println("VisitTO : "+aVisit.getCustomerId());
        
        Visit newVisit = new Visit();
        if (aVisit==null) 
            return newVisit;

        newVisit.setIdvisit(aVisit.getId());
        newVisit.setStartDate(aVisit.getStartDate());
        newVisit.setEndDate(aVisit.getEndDate());
        newVisit.setUnitId(UnitUtility.getAsEntity(aVisit.getUnitId()));
        newVisit.setType(aVisit.getType());

        return newVisit;    
    }
    
    public static List<Visit>getAsEntity(List <VisitTO> visitList) {
        List <Visit> newList = new ArrayList();
        for (VisitTO eachVisit : visitList) {
            newList.add(getAsEntity(eachVisit));
        }
        return newList;
    }
}
