/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-d348116 modeling language!*/


import java.sql.Date;

// line 2 "model.ump"
// line 8 "model.ump"
public class Purchase
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Purchase Attributes
  private String client_name;
  private Date purchase_date;
  private double purchase_total;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Purchase(String aClient_name, Date aPurchase_date, double aPurchase_total)
  {
    client_name = aClient_name;
    purchase_date = aPurchase_date;
    purchase_total = aPurchase_total;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setClient_name(String aClient_name)
  {
    boolean wasSet = false;
    client_name = aClient_name;
    wasSet = true;
    return wasSet;
  }

  public boolean setPurchase_date(Date aPurchase_date)
  {
    boolean wasSet = false;
    purchase_date = aPurchase_date;
    wasSet = true;
    return wasSet;
  }

  public boolean setPurchase_total(double aPurchase_total)
  {
    boolean wasSet = false;
    purchase_total = aPurchase_total;
    wasSet = true;
    return wasSet;
  }

  public String getClient_name()
  {
    return client_name;
  }

  public Date getPurchase_date()
  {
    return purchase_date;
  }

  public double getPurchase_total()
  {
    return purchase_total;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "client_name" + ":" + getClient_name()+ "," +
            "purchase_total" + ":" + getPurchase_total()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "purchase_date" + "=" + (getPurchase_date() != null ? !getPurchase_date().equals(this)  ? getPurchase_date().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}