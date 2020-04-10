package model;
import java.util.*;
public class Ship{
	private final static int Q_CLIENTS = 5;
	private final static double MAX_WEIGTH = 28000;
	private ArrayList<Load> shipments;
	private Client[] clients;
	private boolean shippingPossibility;
	private double warePrice;
	private double wareWeight;

	/**
		*Name:Ship
		*Ship class constructor. 
	*/
	public  Ship() {
		shippingPossibility = false;
		warePrice = 0;
		wareWeight = 0;
		clients = new Client[Q_CLIENTS];
		shipments = new ArrayList<Load>();
	}

	/**
		*Name:getWarePrice
		*calculates and returns the total Ware price(add of all the individual load prices).
		*@return total ware price.
	*/
	public double getWarePrice() {
		calcualateWareprice();
		return warePrice;
	}

	/**
		*Name:calcualateWareprice.
		*calculates the total Ware price(add of all the individual load prices).
		*<b> pre ArrayList shipments must be previously defined.
		*@return total ware price.
	*/
	public void calcualateWareprice() {
		warePrice = 0;
		for (int i = 0 ; i<shipments.size() ; i++ ) {
			warePrice += shipments.get(i).getPrice();
		}
	}

	/**
		*Name: getWareWeight.
		*calculates and returns the total Ware weight(add of all the individual load weights).
		*@return total ware weight.
	*/
	public double getWareWeight() {
		calcualateWareWeight();
		return wareWeight;
	}

	/**
		*Name: calcualateWareWeight.
		*calculates the total Ware weight(add of all the individual load weights).
		*<b> pre ArrayList shipments must be previously defined.
		*@return total ware weights.
	*/
	public void calcualateWareWeight() {
		wareWeight  = 0;
		for (int i = 0 ; i<shipments.size() ; i++ ) {
			wareWeight += shipments.get(i).getTotalWeight();
		}
	}

	/**
		*Name: getClients.
		*returns an array of clients.
		*@return clients[].
	*/
	public Client[] getClients() {
		return clients;
	}

	/**
		*Name: registerClient.
		*registrates a new client, creates an object Client and adds it to 
		*the ship clients array.
		*<b> pre the array clients must be defined.
		*<b> pre the cliet number must represent a valid and empty possition in the clients array. 
		*@param clientNumber must be in between one and Q_CLIENTS . clientNumber > 0 && clientNumber <= Q_CLIENTS .
		*@param name the String name must be != null, and  name must be != "".
		*@param commercialRegister the String commercialRegister must be != null, and commercialRegister must be != "".
		*@param date the Date date must be != null, it must be a date grater than the current date.
		*@param type the type must be in between one and four. type >0 && type <= 4.
		*@param qkgTransported must be in between cero and MAX_VALUE. qkgTransported >= 0 && qkgTransported <= MAX_VALUE.
		*@param totalPaid must be in between cero and MAX_VALUE. totalPaid >= 0 && totalPaid <= MAX_VALUE.
	*/	
	public void registerClient(int clientNumber,String name, String commercialRegister, Date date,int type,double qKgTransported, double totalPaid) {
		clients[clientNumber] = new Client(name,commercialRegister,date,type,qKgTransported,totalPaid);
	}

	/*public Client updateClientData(Client client) {
		return client;
	}*/

	/**
		*Name: getShipments.
		*returns the shipments, wich is an arraylist of loads.
		*<b> pre ArrayList Shipments must be previously defined and != null.
		*@return ArrayList shipments.
	*/
	public ArrayList<Load> getShipments() {
		
		return shipments;
	}

	/**
		*Name: shippingPossibility.
		*checks if the ship meets the requirements to sail, 
		*and then returns a trut value. it can sail = true, cant sail = false.
		*<b> pre the arraylist shipments must be defined.
		*<b> pre the method  getWareWeight() must return a number >= 0
		*@return shippingPossibility.
	*/	
	public boolean shippingPossibility() {
		shippingPossibility = false;

		if (shipments.size() >= 2 || getWareWeight() >= 12000) {
			shippingPossibility = true;
		}		
		return shippingPossibility;
	}

	/**
		*Name: orderValidity.
		*checks if the order meets the requirements to be added as a load to the current ship shioments
		*and then returns a trut value, if is valid to add the order: true, else: false
		*@param qBoxes must be in between one and MAX_VALUE. qBoxes > 0 && qBoxes <= MAX_VALUE.
		*@param weightOfBoxes must be in between cero (not including cero) and 28000, weightOfBoxes > 0, weightOfBoxes <= 28000
		*@param type must be in between one and tree. type > 0, type < = 3.
		*<b> pre he method  getWareWeight() must return a number >= 0.
		*<b> pre the arraylist shipments must be defined.
		*@return orderValidity.
	*/	
	public boolean orderValidity( int qBoxes, double weightOfBoxes, int type) {
		boolean possibility = false, weightExeded = false, invalidType = false;

		if ((qBoxes*weightOfBoxes)+ getWareWeight() > 28000) {
			weightExeded = true;
		}

		if (getShipments().size() != 0) {
			if (((getShipments().get(0).getIntType() == 1 || getShipments().get(0).getIntType() == 2) && type == 3) || (getShipments().get(0).getIntType() == 3 && (type == 1 || type == 2) ) ){
			invalidType = true;
			}
		}

		if ( !weightExeded && !invalidType ) {
			possibility = true;
		}

		return possibility;
	}

	/**
		*Name: loadShip.
		*adds a Load object, shipment, to the shipments arrayList.
		*@param shipments must be != null
		*<b> pre the arraylist shipments must be defined.
	*/	
	public void loadShip(Load shipment) {
		shipments.add(shipment);
	}

	/**
		*Name: unloadOrder.
		*removes an objet Load from the shipments arrayList.
		*@param order must  represent a valid index in the arrayList shipments
		*therefore it must be in between cero and shipments arrayList size. order >= 0 && order < shipments.size().
		*<b> pre the arraylist shipments must be defined. and its size needs to be greater than 0. shipments.size() > 0.
	*/	
	public void unloadOrder(int order) {
		shipments.remove(order);
	}

	/**
		*Name: unloadShip.
		*removes all the objets Load from the shipments arrayList.
		*<b> pre the arraylist shipments must be defined.
	*/	
	public void unloadShip() {
		shipments.clear();
	}

}