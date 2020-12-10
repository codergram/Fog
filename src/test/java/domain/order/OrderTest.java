/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.order;

import domain.carport.Carport;
import domain.customer.Customer;
import domain.material.Material;
import domain.material.Options;
import domain.material.Tree;
import domain.partslist.Part;
import domain.partslist.Partslist;
import domain.user.User;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

  @Test
  void test() {
    Timestamp ts = new Timestamp(System.currentTimeMillis());

    User employee = new User(1, "Sælger john", "email", User.Role.Employee);
    Customer customer =
        new Customer(1, "Navn", "Addresse", 1234, "Bynavn", 12345678, "email@nu.dk");

    Partslist partslist = new Partslist();
    partslist.addItem(
        new Part(
            new Tree("20x20 bjælke", 100, 20, Tree.Usage.Door, Tree.Type.Boards, Material.Unit.Stk),
            2,
            "Bjælke til siden" // total price: 300
            ));
    partslist.addItem(
        new Part(
            new Options("Søm", 0.25, Options.Usage.Door, Options.Type.Screw, Material.Unit.Stk),
            200,
            "Søm til vægbeklædning" // total price: 50
            ));


    Carport carportOne = new Carport(1, 6500, 3020, Carport.Roof.Flat, null, partslist);

    Order expectedOrder =
        new Order(1, 3000, 6500, ts, employee, customer, Order.Status.New, carportOne, 0.0);


    Order actualOrder =
        new Order(2, 3000, 6500, ts, employee, customer, Order.Status.New, carportOne, 0.0);

    assertEquals(expectedOrder.getCarport().getLength(), actualOrder.getCarport().getLength());
    assertEquals(expectedOrder.getCarport().getWidth(), actualOrder.getCarport().getWidth());
    assertEquals(90.0, actualOrder.getCarport().getPrice());
  }
}
