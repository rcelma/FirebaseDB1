package net.rcelma.feb13_17_firebasedb;

/**
 * Created by User on 2/13/2017.
 */

public class Pollo {
	private String uid;
	private String color;
	private String gender;
	private Integer age;
	private String forKevin;

	public Integer getAge() {

		return age;
	}

	public void setAge(Integer age) {

		this.age = age;
	}

	public String getColor() {

		return color;
	}

	public void setColor(String color) {

		this.color = color;
	}

	public String getForKevin() {

		return forKevin;
	}

	public void setForKevin(String forKevin) {

		this.forKevin = forKevin;
	}

	public String getGender() {

		return gender;
	}

	public void setGender(String gender) {

		this.gender = gender;
	}

	public String getUid() {

		return uid;
	}

	public void setUid(String uid) {

		this.uid = uid;
	}

	@Override
	public String toString(){
		String ret = "The Pollo Color: ".concat(color).concat(" Gender: ").concat(gender).concat(" Age: " + age);
		return ret;
	}
}