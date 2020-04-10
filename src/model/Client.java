package model;
import java.util.*;
public class Client{
	private final static int NORMAL = 1;
	private final static int SILVER = 2;
	private final static int GOLD = 3;
	private final static int PLATINUM = 4;
	private String name;
	private String commercialRegister;
	private int type;
	private double qKgTransported;
	private double totalPaid;
	private Date  expirationDate;
	private ArrayList<Load> orders;

	/**
		*Name:Client
		*Client class constructor. 
	*/
	public Client(String name, String commercialRegister, Date expirationDate, int type, double qKgTransported, double totalPaid) {
		this.name = name;
		this.commercialRegister = commercialRegister;
		this.type = type;
		this.qKgTransported = qKgTransported;
		this.totalPaid = totalPaid;
		this.expirationDate = expirationDate;
		orders = new ArrayList<Load>();
	}

	/**
		*Name:getName
		*returns the name of the client.
		*@return name.
	*/
	public  String getName() {
		return name;
	}

	/**
		*Name:setName
		*recives and sets the name of the client.
		*@param name must be != null, and name != "".
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
		*Name:getCommercialRegister.
		*returns the commercialRegister of the client.
		*@return commercialRegister.
	*/
	public String getCommercialRegister () {
		return commercialRegister;
	}

	/**
		*Name:setCommercialRegister.
		*recives and sets the CommercialRegister of the client.
		*@param CommercialRegister must be != null, and CommercialRegister != "".
	*/
	public void setCommercialRegister(String commercialRegister) {
		this.commercialRegister = commercialRegister;
	}

	/**
		*Name:getType
		*gives the type(int) a String value and returns the type of the Client expresed as String. 
		*@return type.
	*/
	public String getType() {
		this.type = 0;
		String type = "Normal";
		if (this.totalPaid >= 5000000) {
			type = "Platinum";
			this.type = 4;
		}else if (this.qKgTransported >= 55000 || this.totalPaid >= 2000000) {
			type = "Gold";
			this.type = 3;			
		}else if (this.qKgTransported >= 35000) {
			this.type = 2;
			type = "Silver";
		}
		return type;
	}

	/**
		*Name: getQKgTransported.
		*calculates the total of Kg transported by the client and returns the total of Kg transported .
		*@return QKgTransported.
	*/
	public double getQKgTransported() {
		calculateQKgTransported();
		return qKgTransported;
	}

	/**
		*Name: calculateQKgTransported.
		*calculates the total of Kg transported by the client by adding all the individual orders total weight. 
		*<b> pre owners arraylistd must be previously defined.
		*<b> pre getTotalWeight() must be in between cero (not including cero) and 28000, getTotalWeight() > 0,  getTotalWeight() <= 28000
	*/
	public void calculateQKgTransported() {
		double qKgTransported = 0;
		for (int i  = 0 ; i < getOrders().size() ; i++ ) {
			qKgTransported += getOrder(i).getTotalWeight();
		}
		this.qKgTransported += qKgTransported;
	}

	/**
		*Name:setQKgTransported.
		*recives and sets the total of Kg transported by the client.
		*@param qKgTransported must be in between cero and MAX_VALUE, qKgTransported > 0,  qKgTransported <= MAX_VALUE.
	*/
	public void setQKgTransported(double qKgTransported) {
		this.qKgTransported = qKgTransported;
	}

	/**
		*Name: getTotalPaid.
		*calculates the total total paid by the client and returns the total paid.
		*@return totalPaid.
	*/
	public double getTotalPaid() {
		calculateTotalPaid();
		return totalPaid;
	}

	/**
		*Name: calculateTotalPaid.
		*calculates the total paid by the client by adding all the individual orders prices, and the. 
		*<b> pre owners arraylistd must be previously defined.
		*<b> pre getTotalWeight() must be in between cero (not including cero) and 28000, getTotalWeight() > 0,  getTotalWeight() <= 28000
	*/
	public void calculateTotalPaid() {
		double totalPaid = 0;
		for (int i  = 0 ; i < getOrders().size() ; i++ ) {
			totalPaid += getOrder(i).getPrice();
		}
		this.totalPaid += totalPaid;
	}

	/**
		*Name:setTotalPaid.
		*recives and sets the total paid by the client.
		*@param totalPaid must be in between cero and MAX_VALUE, totalPaid > 0,  totalPaid <= MAX_VALUE.
	*/
	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;		

	}

	/**
		*Name: getExpirationDate.
		*gets the individual expiration Date attributes, and the concatenate them together 
		*as a string, following the form: (DD/MM/YYYY).
		*@return String.
	*/
	public String getExpirationDate() {
		return expirationDate.getDay()+"/"+expirationDate.getMonth()+"/"+expirationDate.getYear();

	}

	/**
		*Name:setExpirationDate.
		*recives and sets the expiration date of client.
		*@param expirationDate must be !=null.
	*/
	public void setExpirationDate(Date date) {
		this.expirationDate = date;

	}

	/**
		*Name: getorders.
		*gets the arraylist of the clients orders.
		*@return orders.
	*/
	public ArrayList<Load> getOrders() {
		return orders;
	}

	/**
		*Name: showOrder.
		*gets the indivudal information of an order in the arraylist orders, and concatenates it 
		*in an string to organice the information.
		*@param order must  represent a valid index in the arrayList orders
		*therefore it must be in between cero and orders arrayList size. order >= 0 && order < orders.size().
		*<b> orders must be previously defined.
		*<b> orders must conatain al least one Load.
		*<b> orders.get(order) !=null.
		*@return String.
	*/
	public String showOrder(int order) {
		order-=1;
			return "# of boxes: "+orders.get(order).getQBoxes()+
			"\nWeigth of boxes: "+orders.get(order).getWeightOfBoxes()+"Kg."+
			"\nTotal weigth: "+orders.get(order).getTotalWeight()+"Kg."+
			"\nType of load: "+orders.get(order).getType()+
			"\nTotal price: "+(int)orders.get(order).getPrice()+"$.";
	}

	/**
		*Name: getorder.
		*gets a single order out of the ArrayList of orders.
		*@param order must  represent a valid index in the arrayList orders
		*therefore it must be in between cero and orders arrayList size. order >= 0 && order < orders.size().
		*@return order.
	*/
	public Load getOrder(int order) {
		return orders.get(order);
	}

	/**
		*Name: addOrder.
		*adds a Load object(order) to the orders arrayList.
		*@param order must be != null
		*<b> pre the arraylist orders must be defined.
	*/	
	public void addOrder(Load order) {
		orders.add(order);
	}

	/**
		*Name: removeOrder.
		*removes an objet Load from the orders arrayList.
		*@param order must  represent a valid index in the arrayList shipments
		*therefore it must be in between cero and orders arrayList size. order >= 0 && order < orders.size().
		*<b> pre the arraylist orders must be defined. and its size needs to be greater than 0. shipments.size() > 0.
	*/	
	public void removeOrder(int order) {
		orders.remove(order);
	}

	/**
		*Name: getDiscount.
		*gives the type(int) a double value that represents the discount percentage
		*and returns the discount.
		*<b> pre the type be defined. and it needs to be between one and four, type > 0 && type <= 4.
		*@return discount.
	*/	
	public double getDiscount() {
		double discount = 1;
		switch (type) {
			case NORMAL:
				discount = 1;
				break;
			case SILVER:
				discount = 0.985;
				break;
			case GOLD:
				discount = 0.97;
				break;
			case PLATINUM:
				discount = 0.95;
				break;
		}
		return discount;
	}

}