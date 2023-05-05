package entities;

public class Product {
int id, stock;
String name, description;
double price;
boolean shippingIncluded;

public void setId(int id) {
	this.id = id;
}
public int getId() {
	return id;
}

public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public boolean isShippingIncluded() {
	return shippingIncluded;
}
public void setShippingIncluded(boolean shippingIncluded) {
	this.shippingIncluded = shippingIncluded;
}

}
