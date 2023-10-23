package project_0.layers.models;

public class Car {

	int c_id;
	String name;
	String color;
	String description;

	public Car() {
		super();
	}
	
	public Car(int id) {
		super();
		this.c_id = id;
	}

	public Car(String name, String color, String description) {
		super();
		this.name = name;
		this.color = color;
		this.description = description;
	}

	public Car(int c_id, String name, String color, String description) {
		super();
		this.c_id = c_id;
		this.name = name;
		this.color = color;
		this.description = description;
	}

	public int getId() {
		return c_id;
	}

	public void setId(int c_id) {
		this.c_id = c_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Car [c_id=" + c_id + ", name=" + name + ", color=" + color + ", description=" + description + "]";
	}

	
	
}
