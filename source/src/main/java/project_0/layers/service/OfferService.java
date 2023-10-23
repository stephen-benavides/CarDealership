package project_0.layers.service;

import java.util.ArrayList;
import java.util.List;

import project_0.layers.dao.OfferDAO;
import project_0.layers.dao.OfferDAOImpl;
import project_0.layers.models.Offer;

public class OfferService {

	public static OfferDAO od = new OfferDAOImpl();

	public static boolean addOffer(Offer o) {
		return od.addOffer(o);
	}

	public static Offer reviewOffer(int c_id) {
		return od.reviewOffer(c_id);
	}

	public static List<Offer> getPayments(int u_id) { // ------
		return od.getPayments(u_id);
	}

	public static List<Offer> getAllOffers() {
		return od.getAllOffers();
	}

	public static boolean acceptOffer(int o_id, int c_id) {
		return od.acceptOffer(o_id, c_id);
	}

	/*
	 * Business Oriented
	 * 
	 * Setting up getAllOffer (which invokes all the offers) and generate 2
	 * specialized methods
	 * 
	 * case 2: View current offers (get all offers where accepted = 0)
	 * 
	 * case 4: view all payments (get all offers where accepted = 1)
	 * 
	 */

	// Case 2
	public List<Offer> getAllCurrentOffers() {

		List<Offer> offers = this.od.getAllOffers();
		List<Offer> offerA0 = new ArrayList<Offer>();
		
		for(Offer o : offers) {
			if(o.getAceppted() == 0) {
				offerA0.add(o);
			}
		}
		return offerA0;

	}
	
	// Case 4
	public List<Offer> getAllAcceptedOffers() {

		List<Offer> offers = this.od.getAllOffers();
		List<Offer> offerA1 = new ArrayList<Offer>();
		
		for(Offer o : offers) {
			if(o.getAceppted() == 1) {
				offerA1.add(o);
			}
		}
		return offerA1;

	}
	
	//Creating a list to get all the offers for the user to calculate the user's payments
	
//	public List<Offer> getUserDebt(int u_id){
//		List<Offer> payments = this.od.getPayments(u_id);
//		List<Offer> myPayments = new ArrayList<Offer>();
//		
//		for(Offer o : payments) {
//			if(o.getAceppted() == 1) {
//				myPayments.add(new Offer(o.getAmount()));
//			}
//		}
//		return myPayments;
//	}

}
