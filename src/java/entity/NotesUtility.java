/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
public class NotesUtility {

    public static NotesTO getAsTO(Notes aNote) {
        NotesTO newNote = new NotesTO();

        //Customer responsible for relationship
        //newNote.setCustomerId(aNote.getCustomerId().getId());
        newNote.setId(aNote.getIdnotes());
        newNote.setNotes(aNote.getNotes());

        return newNote;
    }

    public static Notes getAsEntity(NotesTO aNote) {
        Notes newNote = new Notes();

        //Customer responsible for relationship
        //newNote.setCustomerId(aNote.getCustomerId().getId());
        newNote.setIdnotes(aNote.getId());
        newNote.setNotes(aNote.getNotes());

        return newNote;
    }

    public static Collection<NotesTO> getAsTO(Collection<Notes> notes) {
        List<NotesTO> newNotes = new ArrayList();
        if (notes!=null)
        for (Notes eachNote : notes) {
            newNotes.add(getAsTO(eachNote));
        }
        return newNotes;
    }

    public static Collection<Notes> getAsEntity(Collection<NotesTO> notes) {
        List<Notes> newNotes = new ArrayList();
        if (notes!=null)
        for (NotesTO eachNote : notes) {
            newNotes.add(getAsEntity(eachNote));
        }
        return newNotes;
    }
}
