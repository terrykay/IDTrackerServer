/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.NextOfKinTO;
import IDTrackerTO.NotesTO;
import IDTrackerTO.ReceiptTO;
import static entity.ReceiptUtility.getAsTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tezk
 */
public class NextOfKinUtility {

    public static NextOfKinTO getAsTO(NextOfKin aNOK) {
        NextOfKinTO newNOK = new NextOfKinTO();

        //Customer responsible for relationship
        //newNote.setCustomerId(aNote.getCustomerId().getId());
        newNOK.setId(aNOK.getId());
        newNOK.setName(aNOK.getName());
        newNOK.setContactNo(aNOK.getContactNo());
        newNOK.setAwareNaturist(aNOK.getAwareNaturist());
        newNOK.setRelationship(aNOK.getRelationship());
        
        return newNOK;
    }

    public static NextOfKin getAsEntity(NextOfKinTO aNOK) {
        NextOfKin newNOK = new NextOfKin();

        //Customer responsible for relationship
        //newNote.setCustomerId(aNote.getCustomerId().getId());
        newNOK.setId(aNOK.getId());
        newNOK.setName(aNOK.getName());
        newNOK.setContactNo(aNOK.getContactNo());
        newNOK.setAwareNaturist(aNOK.getAwareNaturist());
        newNOK.setRelationship(aNOK.getRelationship());

        return newNOK;
    }

    public static Collection<NextOfKinTO> getAsTO(Collection<NextOfKin> noks) {
        List<NextOfKinTO> newNOK = new ArrayList();
        if (noks!=null)
        for (NextOfKin eachNOK : noks) {
            newNOK.add(getAsTO(eachNOK));
        }
        return newNOK;
    }

    public static Collection<NextOfKin> getAsEntity(Collection<NextOfKinTO> noks) {
        List<NextOfKin> newNOK = new ArrayList();
        if (noks!=null)
        for (NextOfKinTO eachNOK : noks) {
            newNOK.add(getAsEntity(eachNOK));
        }
        return newNOK;
    }
}
