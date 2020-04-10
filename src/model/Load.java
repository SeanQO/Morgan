package model;
public class Load{
	private final static int PERISHABLE = 1;
	private final static int NON_PERISHABLE = 2;
	private final static int DANGEROUS = 3;
	private Client owner;
	private int qBoxes;
	private double weightOfBoxes;
	private int type;
	private double price;

	/**
		*Name:Load
		*Load class constructor.
		*@param qBoxes must be in between one and MAX_VALUE. qBoxes > 0 && qBoxes <= MAX_VALUE.
		*@param weightOfBoxes must be in between cero (not including cero) and 28000, weightOfBoxes > 0, weightOfBoxes <= 28000
		*@param type must be in between one and tree. type > 0, type < = 3.
		*@param owner must be != null.
	*/
	public  Load(int qBoxes, double weightOfBoxes, int type, Client owner) {
		this.qBoxes = qBoxes;
		this.weightOfBoxes = weightOfBoxes;
		this.type = type;
		this.owner = owner; 
	}

	/**
		*Name:getQBoxes
		*returns the number of boxes of the load.
		*@return qBoxes.
	*/
	public int getQBoxes() {
		return qBoxes;
	}


	/**
		*Name:setQBoxes
		*recives and sets the number of existing boxes in the load
		*@param qboxes must be in between one and MAX_VALUE. qBoxes > 0 && qBoxes <= MAX_VALUE.
	*/
	public void setQBoxes(int qBoxes) {
		this.qBoxes = qBoxes;
	}

	/**
		*Name:getWeightOfBoxes
		*returns the weight of an individual boxes that represents the weight of all boxes in the load.
		*@return weightOfBoxes.
	*/
	public double getWeightOfBoxes() {
		return weightOfBoxes;

	}

	/**
		*Name:setWeightOfBoxes
		*recives and sets the weight of  an individual box in the load
		*@param qboxes must be in between one and MAX_VALUE. qBoxes > 0 && qBoxes <= MAX_VALUE.
	*/
	public void setWeightOfBoxes(double weightOfBoxes) {
		this.weightOfBoxes = weightOfBoxes;
	}


	/**
		*Name:getTotalWeight.
		*returns the total weight of the load by multiplaying the # of boxes by the weight.
		*@return weightOfBoxes.
	*/
	public double getTotalWeight() {
		double totalWeight = 0;
		totalWeight = qBoxes*weightOfBoxes;
		return totalWeight;
	}


	/**
		*Name: getIntType
		*returns the type of the load expresed as an integer. 
		*@return type.
	*/
	public int getIntType() {
		return type;
	}

	/**
		*Name: getType
		*gives the type(int) a String value and returns the type of the load expresed as String. 
		*@return type.
	*/
	public String getType() {
		String type = "";
		switch (this.type) {
			case PERISHABLE:
				type = "Perishable";
				break;
			case NON_PERISHABLE:
				type = "Non perishable";
				break;
			case DANGEROUS:
				type = "Dangerous";
		}
		return type;
	}


	/**
		*Name:setType.
		*recives and sets the type of the load.
		*@param type must be in between one and 3. type > 0 && type <= 3.
	*/
	public void setType(int type) {
		this.type = type;
	}

	/**
		*Name: getOwner
		*returns the owner(Client object) of the load 
		*@return owner.
	*/
	public Client getOwner() {
		return owner;
	}

	/**
		*Name: getprice
		*calculates the total price of the order and returns the total price.
		*@return price.
	*/
	public double getPrice() {
		calculateOrderPrice();
		return price;
	}

	/**
		*Name: calculateOrderPrice
		*calculates the total price of the load using the # of boxes, their weight, the type, and the discount. 
		*<b> pre the owner must be previously defined.
		*<b> the getDiscount method must return a value in between cero (cero not icluded) and one. 
		* owner.getDiscount() > 0 && owner.getDiscount() <= 1
		*<b> qBoxes must be != null and greater than cero.
		*<b> weightOfBoxes must be != null and greater than cero.
	*/
	public void calculateOrderPrice() {
		price = 0;
		switch (type) {
			case PERISHABLE:
				price = (qBoxes*weightOfBoxes)*80000*owner.getDiscount();
				break;
			case NON_PERISHABLE:
				price = (qBoxes*weightOfBoxes)*250000*owner.getDiscount();
				break;
			case DANGEROUS:
				price = (qBoxes*weightOfBoxes)*390000*owner.getDiscount();
		}
	}

}