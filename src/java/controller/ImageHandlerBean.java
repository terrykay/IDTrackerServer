/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
        return file.delete();
    }
}
