/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.NotificationPreferencesTO;
import IDTrackerTO.UnitTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tezk
 */
public class NotificationPreferencesUtility {
    
    public static NotificationPreferencesTO getAsTO(NotificationPreferences aNotificationPreferences) {
        NotificationPreferencesTO newNP = new NotificationPreferencesTO();
        
        if (aNotificationPreferences == null)
            return null;
        
        newNP.setCustomerId(aNotificationPreferences.getCustomerId());
        newNP.setEmail(aNotificationPreferences.getEmail());
        newNP.setPost(aNotificationPreferences.getPost());
        newNP.setSms(aNotificationPreferences.getSms());
        
        return newNP;
    }
    
    public static List<NotificationPreferencesTO>getAsTO(List <NotificationPreferences> nPList) {
        List <NotificationPreferencesTO> newList = new ArrayList();
        for (NotificationPreferences eachNP : nPList) {
            newList.add(getAsTO(eachNP));
        }
        return newList;
    }
    
    public static NotificationPreferences getAsEntity(NotificationPreferencesTO aNotificationPreferences) {
        NotificationPreferences newNP = new NotificationPreferences();
        
        if (aNotificationPreferences == null)
            return newNP;
       
        // Don't set customer
        newNP.setEmail(aNotificationPreferences.getEmail());
        newNP.setPost(aNotificationPreferences.getPost());
        newNP.setSms(aNotificationPreferences.getSms());
        
        return newNP;
    }
    
    public static List<NotificationPreferences>getAsEntity(List <NotificationPreferencesTO> unitList) {
        List <NotificationPreferences> newList = new ArrayList();
        for (NotificationPreferencesTO eachNP : unitList) {
            newList.add(getAsEntity(eachNP));
        }
        return newList;
    }
}
