/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import resources.PreferencesBean;

/**
 *
 * @author tezk
 */
@Stateless
public class ImageHandlerBean {
    public boolean removeImage(String url, String userId) {
        PreferencesBean pb = PreferencesBean.getInstance();
        String SAVE_DIR = pb.getPathProperty();

        File file = new File(SAVE_DIR+userId+File.separator+url);
        try {
            System.out.println("Delete : "+file.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(ImageHandlerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file.delete();
    }
}
