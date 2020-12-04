package domain.order;

import domain.carport.Carport;
import domain.customer.Customer;
import domain.material.materials.Material;
import domain.partslist.Part;
import domain.partslist.Partslist;
import domain.user.User;
import org.junit.jupiter.api.Test;


import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    
    
    @Test
    void test(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        User employee = new User(1, "Sælger john", "email", User.Role.Employee);
        Customer customer = new Customer(1,"Navn","Addresse",1234,"Bynavn",12345678,"email@nu.dk");
        
        Partslist partslist = new Partslist();
        partslist.addItem(
                new Part(
                        new Material("20x20 bjælke", 550, 20, "stk.", "Door", "Boards"), 15, "Bjælke til siden" //total price: 300
                ));
        partslist.addItem(
                new Part(
                        new Material("30x10 spær", 430, 12.5, "stk.","Door", "Boards"), 30, "Spær til tagkonstruktion" //total price: 375
                ));
        partslist.addItem(
                new Part(
                        new Material("Søm", 0.25,"stk.", "Door", "Boards"), 200, "Søm til vægbeklædning" //total price: 50
                ));
//        partslist.addItem(
//                new Part(
//                    new Tree("20x20 bjælke", 550, 20, Material.usage.Door, Tree.type.Boards), 15, "Bjælke til siden" //total price: 300
//                ));
//        partslist.addItem(
//                new Part(
//                        new Tree("30x10 spær", 430, 12.5, Material.usage.Door, Tree.type.Boards), 30, "Spær til tagkonstruktion" //total price: 375
//                ));
//        partslist.addItem(
//                new Part(
//                        new Options("Søm", 0.25, Material.usage.Door, Tree.type.Boards), 200, "Søm til vægbeklædning" //total price: 50
//                ));
        
        //Total of parts: 725
        
        
        Carport carportOne = new Carport(1,6500, 3020, "Flat", null, partslist);
        
        Order expectedOrder = new Order(1,3000,6500,ts, employee, customer, Order.Status.New, carportOne);
    
        Carport carportTwo = new Carport(1, expectedOrder.getLength(), expectedOrder.getWidth(), "Flat", null, partslist);
        Order actualOrder = new Order(2,3000,6500,ts, employee, customer, Order.Status.New, carportOne);
        
        
        assertEquals(expectedOrder.getCarport().getLength(), actualOrder.getCarport().getLength());
        assertEquals(expectedOrder.getCarport().getWidth(), actualOrder.getCarport().getWidth());
        assertEquals(725.0, actualOrder.getCarport().getPrice());
    }
    
    
}