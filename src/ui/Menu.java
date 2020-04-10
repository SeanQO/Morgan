package ui;
import model.*;
import java.util.*;
public class Menu{
	private final static int REGISTER_CLIENTS = 1;
	private final static int UPDATE_CLIENT_DATA = 2;
	private final static int SHOW_CLIENT_DATA = 3;
	private final static int MAKE_ORDERS = 4;
	private final static int SHOW_ORDERS = 5;
	private final static int SHOW_SHIP_STATUS= 6;
	private final static int UNLOAD_SHIP= 7;
	private final static int MAKE_TRIP= 8;
	private final static int EXIT = 9;
	private final static int Q_SHIPMENTS = 5;
	private Scanner in;
	private Ship thePirate;

	/**
		*Name:Menu
		*Menu class constructor.creates the instance of the Scanner, in. and the principal class (Ship) instance, the pirate.
	*/

	public Menu() {
		in = new Scanner(System.in);
		thePirate = new Ship();
	}

/*
	**************************************************tools*********************************************************
*/

	/**
		*Name:showWellcome
		*it shows a wellcome message for the begining of the program.
	*/
	public void showWellcome(){
		System.out.println("*******************");
		System.out.println("****Wellcome!****");
	}

	/**
		*Name: showPrincipalMenu
		*it shows the pricipal menu interface.
	*/
	public void showPrincipalMenu(){
		System.out.println("*******************");
		System.out.println("**PRINCIPAL MENU***");
		System.out.println("*******************");
		System.out.println("1.Registrate Clients.");
		System.out.println("2.Update client data.");
		System.out.println("3.Show client data.");
		System.out.println("4.Load Ship.");
		System.out.println("5.Show current orders.");
		System.out.println("6.Show ship current status.");
		System.out.println("7.Unload ship.");
		System.out.println("8.Make trip.");
		System.out.println("9.Exit.");
	}

	/**
		*Name:readInt
		*it read an integer number and returns that number.
		*@return option
	*/
	public int readInt() {
		int option = in.nextInt();
		in.nextLine();
		return option;
	}

	/**
		*Name: readDouble
		*it read a double number and returns that number.
		*@return option
	*/
	public double readDouble() {
		double option = in.nextDouble();
		in.nextLine();
		return option;
	}

	/**
		*Name: pressEnterToContinue.
		*shows a message to press enter, reads any input. 
	*/
	public void pressEnterToContinue() {
		System.out.println("*******************");
		System.out.println("Press enter to continue. ");
		String any = in.nextLine();
	}

	/**
		*Name: checkOption.
		*checks if an number given is in between other two numbers, if its not, shows an error message,
		*and then gives a trut value dependig if the number given meets the conditions.
		*@param option != null.
		*@param min != null.
		*@param max != null.
		*@return checked.
	*/
	public boolean checkOption(double option,double min, double max) {
		boolean checked = false;

		if(option >= min && option <= max){
			checked = true; 	
		}else{
			System.out.println("*******************");
			System.out.println("Invalid option.");
		}
		return checked;
	}

	/**
		*Name: enterDate.
		*reads a date, validates if its a valid expedition date, if it is, creates a new expedition date
		* if is not, shows an error message. does this inside a dowhile loop until the user inputs a valid date.
		*finally returns that date.
		*@return date.
	*/
	public model.Date enterDate() {
		boolean exit = false;
		model.Date date;
		do{
			String tempDate = in.nextLine();
			if (model.Date.validate(tempDate)) {
				int d = Integer.parseInt(tempDate.substring(0,2));
				int m = Integer.parseInt(tempDate.substring(3,5));	
				int y = Integer.parseInt(tempDate.substring(6,10));			
				date = new model.Date(d,m,y);	
				exit = true;
			}else{
				date = new model.Date(0,0,0);
				exit = false;
				System.out.println("*******************");
				System.out.println("Invalid date. Please enter a new one (DD/MM/YYYY):");
			}
		
		}while(exit == false);

		return date;
	}	

/*
	**************************************************all options*********************************************************
*/

	/**
		*Name: runOption.
		*with a swich loop, runs all the options avalible in the interface menu, depending what option the user wants to use.
		*<b> thePirate object from Ship class, must be already defined.
		*@param option != null.
	*/
	public void runOption(int option) {
		switch (option) {
		case REGISTER_CLIENTS:
			if (thePirate.getClients()[thePirate.getClients().length-1] == null) {
				runOptionRegisterClients();				
			}else{
				System.out.println("*******************");
				System.out.println("The clients are alredy registered.");
				System.out.println("to update client data, go to option 2 in the principal menu.");
			}
			break;
		case UPDATE_CLIENT_DATA:
			if (thePirate.getClients()[thePirate.getClients().length-1] != null) {
				runOptionUpdateClientData();	
			}else{
				System.out.println("*******************");
				System.out.println("There are not registered clients.");
				System.out.println("to register the clients, go to option 1 in the principal menu.");
			}
			break;

		case SHOW_CLIENT_DATA:
			if (thePirate.getClients()[thePirate.getClients().length-1] != null) {
				RunOptionShowClientData();	
			}else{
				System.out.println("*******************");
				System.out.println("There are not registered clients.");
				System.out.println("to register the clients, go to option 1 in the principal menu.");
			}
			break;

		case MAKE_ORDERS:
			if (thePirate.getClients()[thePirate.getClients().length-1] != null) {
				runOptionMakeOrder();	
			}else{
				System.out.println("*******************");
				System.out.println("There are not registered clients.");
				System.out.println("to register the clients, go to option 1 in the principal menu.");
			}
			break;

		case SHOW_ORDERS:
			System.out.println(thePirate.getShipments().size());
			if (thePirate.getClients()[thePirate.getClients().length-1] != null && thePirate.getShipments().size() != 0) {
				runOptionShowOrders();	
			}
			if(thePirate.getClients()[thePirate.getClients().length-1] == null){
				System.out.println("*******************");
				System.out.println("There are not registered clients.");
				System.out.println("to register the clients, go to option 1 in the principal menu.");
			}else if(thePirate.getShipments().size() == 0){
				System.out.println("*******************");
				System.out.println("There are no orders.");
				System.out.println("to make a new order, go to option 4 in the principal menu.");
			}
			
			break;

		case SHOW_SHIP_STATUS:
			System.out.println("*******************");
			System.out.println("to make a trip you need to have at least two orders or a total weight of 12000Kg.");
			runOptionShowShipStatus();
			break;

		case UNLOAD_SHIP:
			if(thePirate.getShipments().size() == 0){
				System.out.println("*******************");
				System.out.println("There are no orders.");
				System.out.println("to make a new order, go to option 4 in the principal menu.");
			}else{
			runOptionUnloadShip();	
			}
			break;

		case MAKE_TRIP:
			if (thePirate.shippingPossibility()) {
				runOptionMakeTrip();
			}else{
				System.out.println("*******************");
				System.out.println("to make a trip you need to have at least two orders or a total weight of 12000Kg.");
				System.out.println("current number of orders: "+thePirate.getShipments().size());
				System.out.println("current ship weight: "+thePirate.getWareWeight());
				pressEnterToContinue();
			}
			break;

		case EXIT:
			System.out.println("*******************");
			System.out.println("**COME BACK SOON! :)*");
			System.out.println("*******************");
			break;
		default:
			System.out.println("*******************");
			System.out.println("Invalid option.");
		}
	}


/*
	**************************************************Option OptionRegisterClients*********************************************************
*/

	/**
		*Name: runOptionRegisterClients.
		*registers a new cliet by reading and validating all the invidual information needed to create a Client
		*then adds the client to the ship clients array.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array form Ship class, must be already defined.
	*/
	private void runOptionRegisterClients() {
		for (int i = 0; i < thePirate.getClients().length ; i++ ) {
			System.out.println("*******************");
			System.out.println("Enter client #"+(i+1)+" name:");
			String name = in.nextLine();
			System.out.println("*******************");
			System.out.println("Enter client #"+(i+1)+" commercial register:");
			String commercialRegister = in.nextLine();
			boolean exit = false;
			System.out.println("*******************");
			System.out.println("Enter client #"+(i+1)+" expiration date (DD/MM/YYYY):");
			model.Date expirationDate = enterDate();

				

			double qKgTransported = 0;
			do{
				System.out.println("Enter client #"+(i+1)+" total Kg Transported:");
				qKgTransported = readDouble();
			}while(!checkOption(qKgTransported,0,Double.MAX_VALUE));

			double totalPaid = 0;
			do{
				System.out.println("Enter client #"+(i+1)+" total paid:");
				totalPaid = readDouble();
			}while(!checkOption(totalPaid,0,Double.MAX_VALUE));



			thePirate.registerClient(i,name,commercialRegister,expirationDate,1,qKgTransported,totalPaid);
			System.out.println("Client #"+(i+1)+" Type:\n"
								+thePirate.getClients()[i].getType()+".");
			System.out.println("*******************");
		}

	}


/*
	**************************************************Option OptionUpdateClientData*********************************************************
*/

	/**
		*Name: runOptionUpdateClientData.
		*shows the registered clients, the user picks one of them,
		*then it shows what information can the user change from that client, the user selects what to modify
		*with the shitch, based on that option
		*reads the new information, and sets this new information to the client.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. 
	*/
	public void runOptionUpdateClientData() {
		int client;
		do{
			System.out.println("*******************");
			System.out.println("Enter the client number to update info:");
			for (int i = 0; i < thePirate.getClients().length ; i++ ) {
				System.out.print((i+1)+"."+thePirate.getClients()[i].getName()+" ");
			}
			System.out.println("\n*******************");
			client = readInt();
		}while(checkOption(client,1,thePirate.getClients().length) == false);

		boolean exit = false;

		do{
			System.out.println("*******************");
			System.out.println("what information do you want to update? (Actual Type: "+thePirate.getClients()[client-1].getType()+")");
			System.out.println("1.Name 2.Commercial register 3.Expiration date 4.Total Kg transported 5. totalPaid 6.Exit");
			int option = readInt();

			switch (option) {
				case 1:
					System.out.println("*******************");
					System.out.println("Enter new clients name (old:"+thePirate.getClients()[client-1].getName()+")");
					String name = in.nextLine();
					thePirate.getClients()[client-1].setName(name);
					break;
				case 2:
					System.out.println("*******************");
					System.out.println("Enter new clients commercialRegister (old:"+thePirate.getClients()[client-1].getCommercialRegister()+")");
					String commercialRegister = in.nextLine();
					thePirate.getClients()[client-1].setCommercialRegister(commercialRegister);
					break;
				case 3:
					System.out.println("*******************");
					System.out.println("Enter new clients expiration date (DD/MM/YYYY) (old:"+thePirate.getClients()[client-1].getExpirationDate()+")");
					model.Date date = enterDate();
					thePirate.getClients()[client-1].setExpirationDate(date);
					break;

				case 4:
					System.out.println("******************");
					System.out.println("Enter new total of Kg transported");
					double qKgTransported = readDouble();
					thePirate.getClients()[client-1].setQKgTransported(qKgTransported);
					break;

				case 5:
					System.out.println("******************");
					System.out.println("Enter new total of total paid ");
					double totalPaid = readDouble();
					thePirate.getClients()[client-1].setTotalPaid(totalPaid);					
					break;

				case 6:
					exit = true;
					break;

				default:
					System.out.println("*******************");
					System.out.println("Invalid option.");
			}

			if (!exit && option>=1 && option<=6) {
				System.out.println("*******************");
				System.out.println("Successfull update.");	
			}
		}while(!exit);

	}
/*
	*************************************************option OptionShowClientData**********************************************************
*/

	/**
		*Name: RunOptionShowClientData.
		*shows the registered clients, the user picks one of them,
		*then it shows the information asociated with that client.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. 
	*/
	public void RunOptionShowClientData() {
		int option = 0;
		boolean exit = false;
		do{
			System.out.println("*******************");
			System.out.println("Select  client to check informtion.");

			for (int i = 0; i < thePirate.getClients().length ; i++ ) {
				System.out.print((i+1)+"."+thePirate.getClients()[i].getName()+" ");
			}
			System.out.print((thePirate.getClients().length+1)+".Exit");

			System.out.println("\n*******************");
			option = readInt();	

			if (option == thePirate.getClients().length+1) {
				exit = true;
			}else if(checkOption(option,1,thePirate.getClients().length+1)){
				System.out.println("*******************");
				System.out.println("- name: "+thePirate.getClients()[option-1].getName()+".");
				System.out.println("- commercialRegister: "+thePirate.getClients()[option-1].getCommercialRegister()+".");
				System.out.println("- expiration date: "+thePirate.getClients()[option-1].getExpirationDate()+".");
				System.out.println("- type: "+thePirate.getClients()[option-1].getType()+".");
				System.out.println("- total kg transported: "+thePirate.getClients()[option-1].getQKgTransported()+"Kg.");
				System.out.println("- total paid: "+(int)thePirate.getClients()[option-1].getTotalPaid()+"$.");
				System.out.println("- current registered orders: "+thePirate.getClients()[option-1].getOrders().size()+".");
				pressEnterToContinue();
			}

		}while(!exit);

	}
/*
	**************************************************option OptionMakeOrder*********************************************************
*/

	/**
		*Name: runOptionMakeOrder.
		*the user selects an owner with the selectOwner method, 
		*and then it adds an order with the addOrder method.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. 
	*/
	public void runOptionMakeOrder() {
		int owner = 0;
		boolean exit = false;
		do{
			owner = selectOwner();
			if (owner == thePirate.getClients().length+1) {
				exit = true;
			}else{
				addOrder(owner);
			}

		}while(!exit);

	}

	/**
		*Name: selectOwner.
		*shows the registered clients, the user picks one of them.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. thePirate.getClients().length > 0.
	*/
	public int selectOwner() {
		int owner = 0;
		do{
			System.out.println("*******************");
			System.out.println("Select owner to add a new order:");
			for (int i = 0; i < thePirate.getClients().length ; i++ ) {
				System.out.print((i+1)+"."+thePirate.getClients()[i].getName()+" ");
			}
			System.out.print((thePirate.getClients().length+1)+".Exit");
			System.out.println("\n*******************");
			owner = readInt();
		}while(checkOption(owner,1,thePirate.getClients().length+1) == false);

		return owner;
	}

	/**
		*Name: addOrder.
		*create a new order by reading and validating all the invidual information needed to create an order.
		*then adds the order to the ship client orders arraylist.
		*@param owner must  represent a valid index in the array clients
		*therefore it must be in between cero and clients array size. 
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. 
		*<b> orders arraylist must be previously defined.
	*/
	public void addOrder(int owner) {
		int qBoxes = 0;

		do{
			System.out.println("*******************");
			System.out.println("Enter client #"+owner+" number of boxes to transport:");
			qBoxes = readInt();
		}while(!checkOption(qBoxes,1,Double.MAX_VALUE));

		double weightOfBoxes = 0;
		do{
			System.out.println("Enter client #"+owner+" weight of each box:");
			weightOfBoxes = readDouble();
		}while(!checkOption(weightOfBoxes,1,Double.MAX_VALUE));

		if (weightOfBoxes*qBoxes > 28000) {
			System.out.println("*******************");
			System.out.println("The order exceedes the capacity of the ship (max 28000kg current: "+(weightOfBoxes*qBoxes)+")");
		}else{
			int type = 0;
			do{
				System.out.println("Enter client #"+owner+" type of the order:");
				System.out.println("1.Perishable. 2.Non perishable. 3.Dangerous");
				type= readInt();
			}while(!checkOption(type,1,3));		

			if (thePirate.orderValidity(qBoxes,weightOfBoxes,type)) {
				Load order = new Load(qBoxes,weightOfBoxes,type,thePirate.getClients()[owner-1]);	
				thePirate.getClients()[owner-1].addOrder(order);
				confirmOrder(owner);	
			}else{
				showError(qBoxes, weightOfBoxes, type);
			}
		}

	}

	/**
		*Name: showError.
		*shows an error message for when a client tryes to add a new order 
		*with invalid information, that doesnt meet the requirements to add a new order.
		* and displays the reason why it cannot be added as a new order
		*@param qBoxes must be in between one and MAX_VALUE. 
		*@param weightOfBoxes must be in between cero (not including cero) and 28000, 
		*@param type must be in between one and tree. type > 0, type < = 3.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> shipments arraylist must be previously defined.
	*/
	public void showError(int qBoxes, double weightOfBoxes, int type) {
		if ((qBoxes*weightOfBoxes)+ thePirate.getWareWeight() > 28000) {
			System.out.println("*******************");
			System.out.println("Cannot add order because it exedes the current ware weight allowed(28000kg)");
			System.out.println("current: "+thePirate.getWareWeight());
			System.out.println("adding your order: "+( thePirate.getWareWeight()+(qBoxes*weightOfBoxes)));
		}

		if (thePirate.getShipments().size() != 0) {
			if (((thePirate.getShipments().get(0).getIntType() == 1 || thePirate.getShipments().get(0).getIntType() == 2) && type == 3) || (thePirate.getShipments().get(0).getIntType() == 3 && (type == 1 || type == 2) ) ){
			System.out.println("*******************");
			System.out.println("Cannot add order because the type "+thePirate.getShipments().get(0).getType()+" Cannot be shiped with your order type");
			}
		}	
	}

	/**
		*Name: confirmOrder.
		*shows a message to comfirm or discard the order, and with a switch loop, runs either 
		*comfirm, that adds the order to the client orders arraylist or
		*discard, that errases that order from the shipments arraylist
		*@param owner must  represent a valid index in the array clients
		*therefore it must be in between cero and clients array size. 
		*<b> thePirate object from Ship class, must be already defined.
		*<b> clients array size must be diferent from 0. thePirate.getClients().length > 0.
		*<b> orders arraylist must be previously defined.
	*/
	public void confirmOrder(int owner) {
		boolean exit = false;
		do{
			System.out.println("*******************");
			System.out.println("Order:");
			System.out.println(thePirate.getClients()[owner-1].showOrder(thePirate.getClients()[owner-1].getOrders().size()));
			System.out.println("*******************");
			System.out.println("1.Confirm 2.Discard");
			int option = readInt();
			switch (option) {
				case 1:
					exit = true;
					Load load = thePirate.getClients()[owner-1].getOrder(thePirate.getClients()[owner-1].getOrders().size()-1);
					thePirate.loadShip(load);
					System.out.println("*******************");
					System.out.println("Order successfully added to client: "+thePirate.getClients()[owner-1].getName());		
					break;

				case 2:
					thePirate.getClients()[owner-1].removeOrder(thePirate.getClients()[owner-1].getOrders().size()-1);
					exit = true;
					System.out.println("*******************");
					System.out.println("Order successfully discarted");					
					break;

				default:
					System.out.println("*******************");
					System.out.println("Invalid option.");
					exit = false;

			}			

		}while(!exit);

	}

/*
	**************************************************Option runOptionShowOrders *********************************************************
*/

	/**
		*Name: runOptionShowOrders.
		*shows the information realted to the each order from the shipments arraylist
		*<b> thePirate object from Ship class, must be already defined.
		*<b> shipments arraylist must be previously defined.
		*<b> shipments arraylist size must be greater than 0. 
	*/
	public void runOptionShowOrders() {
		System.out.println("\n\n*******************");
		for (int i = 0; i < thePirate.getShipments().size() ; i++ ) {
			System.out.println("*******************");
			System.out.println("order #"+(i+1)+":");
			System.out.println("- Owner: "+thePirate.getShipments().get(i).getOwner().getName()+".");
			System.out.println("- type: "+thePirate.getShipments().get(i).getType());
			System.out.println("- Boxes: "+thePirate.getShipments().get(i).getQBoxes()+".");
			System.out.println("- Weight of boxes: "+thePirate.getShipments().get(i).getWeightOfBoxes()+"Kg.");
			System.out.println("- Total weight of order: "+thePirate.getShipments().get(i).getTotalWeight()+"Kg.");
			System.out.println("- Total price of order: "+(int)thePirate.getShipments().get(i).getPrice()+"$.");
		}
		pressEnterToContinue();
	}

/*
	**************************************************Option runOptionShowShipStatus *********************************************************
*/

	/**
		*Name: runOptionShowShipStatus.
		*shows the information realted to the ship object, thePirate.
		*<b> thePirate object from Ship class, must be already defined.
	*/
	public void runOptionShowShipStatus(){
		System.out.println("*******************");
		System.out.println("- Total ware weight: "+thePirate.getWareWeight()+"Kg.");
		System.out.println("- Total ware price: "+(int)thePirate.getWarePrice()+"$.");
		System.out.println("- Total of orders: "+thePirate.getShipments().size()+".");
		System.out.println("- Shipment possibility: "+thePirate.shippingPossibility()+".");
		pressEnterToContinue();
	}
/*
	**************************************************Option runOptionUnloadShip *********************************************************
*/

	/**
		*Name: runOptionUnloadShip.
		*the user chosses to either unload the ship fully, or unload a single order.
		*if unload the whole ship, it calls the method thePirate.unloadShip.
		*if unload an order, it shows all the orders and the user picks one to unload
		*and then calls the method thePirate.unloadOrder.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> shipments arraylist must be previously defined.
		*<b> shipments arraylist size must be greater than 0. 
	*/
	public void runOptionUnloadShip() {
		boolean exit = false;
		do{
			System.out.println("*******************");
			System.out.println("1.Unload the ship. 2. Unload an order 3.Exit");
			int option = readInt();
			switch (option) {
				case 1:	
					thePirate.unloadShip();
					System.out.println("*******************");
					System.out.println("Ship successfully unloaded, the Pirate is now empty.");
					pressEnterToContinue();
					exit = true;
					break;
				case 2:
					System.out.println("*******************");
					for (int i  = 0 ; i < thePirate.getShipments().size() ; i++ ) {
						System.out.println((i+1)+". -Owner: "+thePirate.getShipments().get(i).getOwner().getName()+
						" -Type: "+thePirate.getShipments().get(i).getType()+
						" - Total Weight: "+thePirate.getShipments().get(i).getTotalWeight()+
						" - Total paid: "+(int)thePirate.getShipments().get(i).getPrice());
					}
					System.out.println("*******************");
					System.out.println("Select an order to unload.");
					option = readInt();
					checkOption(option,1,thePirate.getShipments().size());
					thePirate.unloadOrder(option-1 	);
					System.out.println("*******************");
					System.out.println("Order successfully unloaded, the Pirate has now "+thePirate.getShipments().size()+" Orders.");
					pressEnterToContinue();
					if (thePirate.getShipments().size() == 0) {
						exit = true;
					}		
					break;
				case 3:
					exit = true;
					break;
				default:				
			}				
		}while(exit == false);

	}
/*
	**************************************************Option runOptionMakeTrip *********************************************************
*/

	/**
		*Name: runOptionMakeTrip.
		*shows that the trip is made, shows the info of the trip,
		*and unloads the ship.
		*<b> thePirate object from Ship class, must be already defined.
		*<b> shipments arraylist must be previously defined.
		*<b> shipments arraylist size must be greater than 0. 
	*/
	public void runOptionMakeTrip() {
		System.out.println("*******************");
		System.out.println("Trip successfully made.");
		System.out.println("*******************");
		System.out.println("- Total weight transported: "+thePirate.getWareWeight()+"Kg.");
		System.out.println("- Total price charged: "+(int)thePirate.getWarePrice()+"$.");
		System.out.println("- Total of orders transported: "+thePirate.getShipments().size()+".");
		thePirate.unloadShip();
		System.out.println("*******************");
		System.out.println("Ship fully unloaded.");
		System.out.println("*******************");
		System.out.println("Ship ready for a new trip.");
		pressEnterToContinue();
	}
/*
	**************************************************Start Porgram*********************************************************
*/

	/**
		*Name: startProgram.
		*shows a wellcome message, then shows the principal menu, reads the option selected, and runs that option,
		*repeats until the user selects exit in the pricipal menu
	*/
	public void startProgram() {
		showWellcome();
		int option;
		do{
			showPrincipalMenu();
			option = readInt();
			runOption(option);
		}while(option != EXIT);
	}

}