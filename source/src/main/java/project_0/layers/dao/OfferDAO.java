package project_0.layers.dao;

import java.util.List;

import project_0.layers.models.Offer;
import project_0.layers.models.User;

public interface OfferDAO {

	/*
	 * - add offers (make offers on the car)
	 * - can accept/reject offers (boolean: 1: accept, 0: reject)
	 * 		--reviewOffer (if 1 call procedure to deleteALL)
	 */
	
	//Add Offer
	public boolean addOffer(Offer o);
	
	//Eliminate all offers where accepted = 0
	public Offer reviewOffer(int c_id);
	
	//Get all the payments
	public List<Offer> getPayments(int u_id);
	
	//See all the current offers
	public List<Offer> getAllOffers();
		
	//Accept offers and eliminate the rest
	public boolean acceptOffer(int o_id, int c_id);
}
