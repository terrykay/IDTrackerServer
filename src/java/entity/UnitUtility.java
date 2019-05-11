/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.UnitTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tezk
 */
public class UnitUtility {
    
    public static UnitTO getAsTO(Unit aUnit) {
        UnitTO newUnit = new UnitTO();
        
        if (aUnit == null)
            return newUnit;
        
        newUnit.setId(aUnit.getId());
        newUnit.setMake(aUnit.getMake());
        newUnit.setModel(aUnit.getModel());
        newUnit.setDimensions(aUnit.getDimensions());
        newUnit.setElectricity(aUnit.getElectricity());
        
        return newUnit;
    }
    
    public static List<UnitTO>getAsTO(List <Unit> unitList) {
        List <UnitTO> newList = new ArrayList();
        for (Unit eachUnit : unitList) {
            newList.add(getAsTO(eachUnit));
        }
        return newList;
    }
    
    public static Unit getAsEntity(UnitTO aUnit) {
        Unit newUnit = new Unit();
        
        if (aUnit == null)
            return newUnit;
        
        newUnit.setId(aUnit.getId());
        newUnit.setMake(aUnit.getMake());
        newUnit.setModel(aUnit.getModel());
        newUnit.setDimensions(aUnit.getDimensions());
        newUnit.setElectricity(aUnit.getElectricity());
        
        return newUnit;
    }
    
    public static List<Unit>getAsEntity(List <UnitTO> unitList) {
        List <Unit> newList = new ArrayList();
        for (UnitTO eachUnit : unitList) {
            newList.add(getAsEntity(eachUnit));
        }
        return newList;
    }
}
