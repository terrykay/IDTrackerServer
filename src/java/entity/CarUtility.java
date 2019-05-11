/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import IDTrackerTO.CarTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import session.CustomerFacade;

/**
 *
 * @author tezk
 */
public class CarUtility {
    public static CarTO getAsTO(Car aCar) {
        CarTO newCar = new CarTO();
        newCar.setIdcar(aCar.getIdcar());
        newCar.setMake(aCar.getMake());
        newCar.setModel(aCar.getModel());
        newCar.setRegno(aCar.getRegno());
        newCar.setColour(aCar.getColour());
        newCar.setDateAdded(aCar.getDateAdded());
//        newCar.setCustomerId(aCar.getCustomerId().getId());

        return newCar;
    }

    public static Collection<CarTO> getAsTO(Collection<Car> cars) {
        List<CarTO> newList = new ArrayList();
        for (Car eachCar : cars) {
            newList.add(getAsTO(eachCar));
        }
        return newList;
    }

    public static Car getAsEntity(CarTO aCar) {
        Car newCar = new Car();
        newCar.setIdcar(aCar.getIdcar());
        newCar.setMake(aCar.getMake());
        newCar.setModel(aCar.getModel());
        newCar.setRegno(aCar.getRegno());
        newCar.setColour(aCar.getColour());
        newCar.setDateAdded(aCar.getDateAdded());

        return newCar;
    }
   
    public static Collection<Car> getAsEntity(Collection <CarTO> cars) {
        List<Car> newList = new ArrayList();
        for (CarTO eachCar : cars) {
            newList.add(getAsEntity(eachCar));
        }
        return newList;
    }

}
