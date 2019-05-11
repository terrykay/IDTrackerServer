/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Car;
import entity.Visit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import session.VisitFacade;
import utility.MailHandler;

/**
 *
 * @author tezk
 */
@Stateless
public class VisitorReportBean {

    @EJB
    VisitFacade visitFacade;

    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
    public void myTimer() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Runnable task = new Runnable() {

            public void run() {
                List<Visit> visits = visitFacade.findByVisitDate(new Date(), cal.getTime());
                System.out.println(new Date() + " : " + visits.size() + " visits listed today");

                Collections.sort(visits, new Comparator<Visit>(){
                    @Override
                    public int compare(Visit o1, Visit o2) {
                        return o1.getStartDate().compareTo(o2.getStartDate()); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                if (visits.size() > 0) {

                    StringBuilder stringBuilder = new StringBuilder("Name, Start date, End date, Visit type, size, Hookup, Vehicles\n");

                    for (Visit eachVisit : visits) {
                        stringBuilder.append(eachVisit.getCustomerId().getSurname());
                        stringBuilder.append(", " + eachVisit.getStartDate());
                        stringBuilder.append(", " + eachVisit.getEndDate());
                        stringBuilder.append(", " + eachVisit.getType());
                        stringBuilder.append(", ");
                        if (eachVisit.getUnitId() != null && eachVisit.getUnitId().getDimensions() != null)
                            stringBuilder.append(eachVisit.getUnitId().getDimensions());
                        stringBuilder.append(", ");
                        if (eachVisit.getUnitId() != null)
                            stringBuilder.append(eachVisit.getUnitId().getElectricity());
                        stringBuilder.append(",");
                        Collection<Car> cars = eachVisit.getCustomerId().getCarCollection();
                        if (cars != null && cars.size() > 0) {

                            for (Car eachCar : cars) {
                                stringBuilder.append(" " + eachCar.getRegno());
                            }
                        }
                        stringBuilder.append("\n");
                    }
                    System.out.println("Output\n" + stringBuilder.toString());

                    MailHandler mail = MailHandler.getInstance();
                    mail.setRecipient("tel@tezk.co.uk");
                    mail.setSubject("Visit tests");
                    mail.setEmailBody(stringBuilder.toString());
                    mail.send();
                }

            };

        };
                              new Thread(task).start();
    }

}
