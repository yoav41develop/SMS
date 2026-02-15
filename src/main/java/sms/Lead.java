package sms;

public class Lead {
	private String name;
	private String phone;
	private String recommender;
	
	Lead(String name, String phone, String recommender) {
		this.name = name;
		this.phone = phone;
		this.recommender = recommender;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getRecommender() {
		return recommender;
	}
}