/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.MembershipchargeTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tezk
 */
public class MembershipchargeUtility {

    public static MembershipchargeTO getAsTO(Membershipcharge aCharge) {
        MembershipchargeTO newCharge = new MembershipchargeTO();

        newCharge.setId(aCharge.getId());
        newCharge.setAmount(aCharge.getAmount());
        newCharge.setInvoiceCollection(InvoiceUtility.getAsTO(aCharge.getInvoiceCollection()));
        // Membership relationship controlled be Membership
        newCharge.setMembership(aCharge.getMembershipId().getId());
        newCharge.setNotes(aCharge.getNotes());
        newCharge.setType(aCharge.getType());
        newCharge.setYear(aCharge.getYear());

        return newCharge;
    }

    public static Membershipcharge getAsEntity(MembershipchargeTO aCharge) {
        Membershipcharge newCharge = new Membershipcharge();

        newCharge.setId(aCharge.getId());
        newCharge.setAmount(aCharge.getAmount());
        newCharge.setInvoiceCollection(InvoiceUtility.getAsEntity(aCharge.getInvoiceCollection()));
        // Membership relationship controlled be Membership
        //newCharge.setMembership(membership);
        newCharge.setNotes(aCharge.getNotes());
        newCharge.setType(aCharge.getType());
        newCharge.setYear(aCharge.getYear());

        return newCharge;
    }

    public static Collection<MembershipchargeTO> getAsTO(Collection<Membershipcharge> charges) {
        List<MembershipchargeTO> newCharges = new ArrayList();
        for (Membershipcharge eachCharge : charges) {
            newCharges.add(MembershipchargeUtility.getAsTO(eachCharge));
        }
        return newCharges;
    }

    public static Collection<Membershipcharge> getAsEntity(Collection<MembershipchargeTO> charges) {
        List<Membershipcharge> newCharges = new ArrayList();
        if (charges != null) {
            for (MembershipchargeTO eachCharge : charges) {
                newCharges.add(MembershipchargeUtility.getAsEntity(eachCharge));
            }
        }
        return newCharges;
    }
}
