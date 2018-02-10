/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.ElectricitychargeTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author tezk
 */
public class ElectricitychargeUtility {

    public static ElectricitychargeTO getAsTO(Electricitycharge aCharge) {
        ElectricitychargeTO newCharge = new ElectricitychargeTO();
        newCharge.setId(aCharge.getId());
        newCharge.setStartUnit(aCharge.getStartUnit());
        newCharge.setEndUnit(aCharge.getEndUnit());
        newCharge.setYear(aCharge.getYear());
        newCharge.setDatePaid(aCharge.getDatePaid());
        newCharge.setCharge(aCharge.getCharge());
        // link to Membership is controlled by Membership, we only use the id number
        newCharge.setMembershipId(aCharge.getMembershipId().getId());

        return newCharge;
    }

    public static Collection<ElectricitychargeTO> getAsTO(Collection<Electricitycharge> charges) {
        List<ElectricitychargeTO> newCharges = new ArrayList();
        for (Electricitycharge eachCharge : charges) {
            newCharges.add(ElectricitychargeUtility.getAsTO(eachCharge));
        }
        return newCharges;
    }

    public static Electricitycharge getAsEntity(ElectricitychargeTO aCharge) {
        Electricitycharge newCharge = new Electricitycharge();
        newCharge.setId(aCharge.getId());
        newCharge.setStartUnit(aCharge.getStartUnit());
        newCharge.setEndUnit(aCharge.getEndUnit());
        newCharge.setYear(aCharge.getYear());
        newCharge.setDatePaid(aCharge.getDatePaid());
        newCharge.setCharge(aCharge.getCharge());
        // link to Membership is controlled by Membership - client must retrieve if needed
        //newCharge.setMembershipId(MembershipUtility.getAsEntity(aCharge.getMembershipId()));

        return newCharge;
    }

    public static Collection<Electricitycharge> getAsEntity(Collection<ElectricitychargeTO> charges) {
        List<Electricitycharge> newCharges = new ArrayList();
        if (charges != null) {
            for (ElectricitychargeTO eachCharge : charges) {
                newCharges.add(ElectricitychargeUtility.getAsEntity(eachCharge));
            }
        }
        return newCharges;
    }
}
