/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.InvoiceTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author tezk
 */
public class InvoiceUtility {

    public static InvoiceTO getAsTO(Invoice anInvoice) {
        InvoiceTO newInvoice = new InvoiceTO();

        newInvoice.setInvoicenumber(anInvoice.getInvoicenumber());
        newInvoice.setAmount(anInvoice.getAmount());
        newInvoice.setDuedate(anInvoice.getDuedate());
        newInvoice.setIssuedate(anInvoice.getIssuedate());
        //Membershipcharge relationship controlled by Membershipcharge
       // if (anInvoice.getMembershipchargeCollection().size() > 0)
       //     for (Membershipcharge eachMembershipcharge : anInvoice.getMembershipchargeCollection()) 
       //         newInvoice.getMembershipchargeCollection().add(eachMembershipcharge.getMembershipId().getId());
        
        newInvoice.setNotes(anInvoice.getNotes());

        return newInvoice;
    }

    public static Collection<InvoiceTO> getAsTO(Collection<Invoice> invoices) {
        Collection<InvoiceTO> newInvoices = new ArrayList();

        for (Invoice eachInvoice : invoices) {
            newInvoices.add(getAsTO(eachInvoice));
        }
        return newInvoices;
    }

    public static Invoice getAsEntity(InvoiceTO anInvoice) {
        Invoice newInvoice = new Invoice();
        if (anInvoice == null)
            return newInvoice;

        newInvoice.setInvoicenumber(anInvoice.getInvoicenumber());
        newInvoice.setAmount(anInvoice.getAmount());
        newInvoice.setDuedate(anInvoice.getDuedate());
        newInvoice.setIssuedate(anInvoice.getIssuedate());
        //Membershipcharge relationship controlled by Membershipcharge
        //newInvoice.setMembershipchargeCollection(membershipchargeCollection);
        newInvoice.setMembershipchargeCollection(new HashSet());
        newInvoice.setCustomerIsAttendingEventCollection(new HashSet());
        newInvoice.setNotes(anInvoice.getNotes());

        return newInvoice;
    }

    public static Collection<Invoice> getAsEntity(Collection<InvoiceTO> invoices) {
        Collection<Invoice> newInvoices = new ArrayList();

        for (InvoiceTO eachInvoice : invoices) {
            newInvoices.add(getAsEntity(eachInvoice));
        }
        return newInvoices;
    }
}
