/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.ReceiptTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author tezk
 */
public class ReceiptUtility {

    public static ReceiptTO getAsTO(Receipt aReceipt) {
        ReceiptTO newReceipt = new ReceiptTO();

        newReceipt.setReceiptnumber(aReceipt.getReceiptnumber());
        newReceipt.setAmount(aReceipt.getAmount());
        newReceipt.setDate(aReceipt.getDate());
        // MembershipchargeCollection relationship controlled by MCC
   //     if (aReceipt.getMembershipchargeCollection().size() > 0) {
   //         for (Membershipcharge eachMembershipcharge : aReceipt.getMembershipchargeCollection()) {
   //             newReceipt.getMembershipchargeCollection().add(eachMembershipcharge.getMembershipId().getId());
   //         }
   //     }

        newReceipt.setNotes(aReceipt.getNotes());
        newReceipt.setReceivedby(aReceipt.getReceivedby());

        return newReceipt;
    }

    public static Collection<ReceiptTO> getAsTO(Collection<Receipt> receipts) {
        List<ReceiptTO> newReceipts = new ArrayList();
        for (Receipt eachReceipt : receipts) {
            newReceipts.add(getAsTO(eachReceipt));
        }
        return newReceipts;
    }

    public static Receipt getAsEntity(ReceiptTO aReceipt) {
        Receipt newReceipt = new Receipt();
        if (aReceipt == null) {
            return newReceipt;
        }

        newReceipt.setReceiptnumber(aReceipt.getReceiptnumber());
        newReceipt.setAmount(aReceipt.getAmount());
        newReceipt.setDate(aReceipt.getDate());
        // MembershipchargeCollection relationship controlled by MCC
        //newReceipt.setMembershipchargeCollection(membershipchargeCollection);
        newReceipt.setNotes(aReceipt.getNotes());
        newReceipt.setReceivedby(aReceipt.getReceivedby());

        return newReceipt;
    }

    public static Collection<Receipt> getAsEntity(Collection<ReceiptTO> receipts) {
        List<Receipt> newReceipts = new ArrayList();
        if (receipts != null) {
            for (ReceiptTO eachReceipt : receipts) {
                newReceipts.add(getAsEntity(eachReceipt));
            }
        }
        return newReceipts;
    }

}
