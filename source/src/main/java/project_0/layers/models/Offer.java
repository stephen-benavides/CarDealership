package project_0.layers.models;

public class Offer {

	int id;
	int user_id;
	int car_id;
	int amount;
	int aceppted;
	
	public Offer() {
		super();
	}
	
	public Offer(int amount) {
		super();
		this.amount = amount;
		
	}
	public Offer(int car_id, int amount) {
		super();
		this.car_id = car_id;
		this.amount = amount;
		
	}
	public Offer(int user_id, int car_id, int amount, int aceppted) {
		super();
		this.user_id = user_id;
		this.car_id = car_id;
		this.amount = amount;
		this.aceppted = aceppted;
	}
	public Offer(int id, int user_id, int car_id, int amount, int aceppted) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.car_id = car_id;
		this.amount = amount;
		this.aceppted = aceppted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAceppted() {
		return aceppted;
	}
	public void setAceppted(int aceppted) {
		this.aceppted = aceppted;
	}
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", user_id=" + user_id + ", car_id=" + car_id + ", amount=" + amount + ", aceppted="
				+ aceppted + "]";
	}
	
	
}
