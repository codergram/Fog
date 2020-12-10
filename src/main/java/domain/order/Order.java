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
import domain.user.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Order {

  public enum Status {
    New,
    Awaiting,
    Accepted,
    Completed,
    Cancelled
  }

  private int id;
  private final double width;
  private final double length;
  private final Timestamp timestamp;
  private final User salesEmployee;
  private final Customer customer;
  private final Enum<Status> status;
  private final Carport carport;
  private double margin;
  private UUID uuid;

  public Order(
      int id,
      double width,
      double length,
      Timestamp timestamp,
      User salesEmployee,
      Customer customer,
      Enum<Status> status,
      Carport carport,
      double margin) {
    this.id = id;
    this.width = width;
    this.length = length;
    this.timestamp = timestamp;
    this.salesEmployee = salesEmployee;
    this.customer = customer;
    this.status = status;
    this.carport = carport;
    this.margin = margin;
    this.uuid = null;
  }

  public Order(
      int id,
      double width,
      double length,
      Timestamp timestamp,
      User salesEmployee,
      Customer customer,
      Enum<Status> status,
      Carport carport,
      double margin,
      UUID uuid) {
    this.id = id;
    this.width = width;
    this.length = length;
    this.timestamp = timestamp;
    this.salesEmployee = salesEmployee;
    this.customer = customer;
    this.status = status;
    this.carport = carport;
    this.margin = margin;
    this.uuid = uuid;
  }

  public Order(double width, double length, Customer customer, Carport carport) {
    this.id = -1;
    this.width = width;
    this.length = length;
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.salesEmployee = null;
    this.customer = customer;
    this.status = Order.Status.New;
    this.carport = carport;
    this.margin = 30.0;
    this.uuid = null;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public int getId() {
    return id;
  }

  public double getWidth() {
    return width;
  }

  public double getLength() {
    return length;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public User getSalesEmployee() {
    return salesEmployee;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Enum<Status> getStatus() {
    return status;
  }

  public Carport getCarport() {
    return carport;
  }

  public double getMargin() {
    return margin;
  }

  public void setMargin(double margin) {
    this.margin = margin;
  }

  public boolean hasSalesman() {
    return salesEmployee != null;
  }

  public boolean isPaid() {
    return this.status == Status.Completed;
  }

  public String getOrderDate() {
    Date date = new Date(this.timestamp.getTime());
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    return format.format(date);
  }

  public void setId(int id) {
    this.id = id;
  }
}
