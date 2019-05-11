/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.ImageTO;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tezk
 */
public class ImageUtility {

    public static ImageTO getAsTO(Image anImage) {
        ImageTO newImage = new ImageTO();

        newImage.setId(anImage.getId());
        // CustomerID realtionship controlled by Customer
        newImage.setCustomerId(anImage.getCustomerId().getId());
        newImage.setExpires(anImage.getExpires());
        newImage.setScanned(anImage.getScanned());
        // Entity "Type" field, char 0 == type (i = id, d = document) from 1 on = details
        newImage.setType(anImage.getType());

        newImage.setDetails(anImage.getDetails());
        newImage.setUrl(anImage.getUrl());
        // TODO : Load image from URL
        return newImage;
    }

    public static Image getAsEntity(ImageTO anImage) {
        Image newImage = new Image();

        newImage.setId(anImage.getId());
        // CustomerID realtionship controlled by Customer
        //newImage.setCustomerId(anImage.getCustomerId());
        newImage.setExpires(anImage.getExpires());
        newImage.setScanned(anImage.getScanned());
        // Image type = char (i = id, d = document) details = string of details
        newImage.setType(anImage.getType());
        newImage.setUrl(anImage.getUrl());

        newImage.setDetails(anImage.getDetails());
        // TODO : Load image from URL
        System.out.println("ImageUtility : image = "+newImage);
        return newImage;
    }

    public static Collection<ImageTO> getAsTO(Collection<Image> images) {
        List<ImageTO> newImages = new ArrayList();
        for (Image eachImage : images) {
            newImages.add(getAsTO(eachImage));
        }
        return newImages;
    }

    public static Collection<Image> getAsEntity(Collection<ImageTO> images) {
        List<Image> newImages = new ArrayList();
        for (ImageTO eachImage : images) {
            newImages.add(getAsEntity(eachImage));
        }
        return newImages;
    }
}
