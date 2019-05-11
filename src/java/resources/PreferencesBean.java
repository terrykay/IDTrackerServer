/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.beans.*;
import java.io.Serializable;
import java.util.prefs.Preferences;
import javax.ejb.Stateless;

/**
 *
 * @author tezk
 */

public class PreferencesBean implements Serializable {
    
    
    private static PreferencesBean preferencesBean=null;
    public static PreferencesBean getInstance() {
        if (preferencesBean==null)
            preferencesBean = new PreferencesBean();
        return preferencesBean;
    }
    
    public static final String PROP_PATH_PROPERTY = "pathProperty";
    public static final String PATH = "path";
    
    private String pathProperty;
    
    private PropertyChangeSupport propertySupport;
    
    protected PreferencesBean() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    
    
    public String getPathProperty() {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesBean.class);
        pathProperty = prefs.get(PATH, "");
        return pathProperty;
    }
    
    public void setPathProperty(String value) {
        String oldValue = pathProperty;
        pathProperty = value;
        Preferences prefs = Preferences.userNodeForPackage(PreferencesBean.class);
        prefs.put(PATH, value);
        propertySupport.firePropertyChange(PROP_PATH_PROPERTY, oldValue, pathProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
