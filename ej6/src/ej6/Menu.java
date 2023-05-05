package ej6;

import database.DbHandler;
import java.util.Scanner;
import entities.Product;

public class Menu {
	
	private DbHandler handler = new DbHandler();
	private Scanner lector;
	
	public void start() {
		
		lector = new Scanner(System.in);
		String rta = "";
		System.out.println("Opciones: list/search/new/update/delete");
		do {
			rta = lector.nextLine();
			switch(rta) {
				case "list":
					list();
					break;
				case "search":
					search();
					break;
				case "new":
					newProd();
					break;
				case "update":
					update();
					break;
				case "delete":
					delete();
					break;
			}
		} while(!rta.equals("exit"));
		
		lector.close();
	}
	
	public void list() {
		System.out.println(handler.list());
	}
	
	public void search() {
		Product p = new Product();
		System.out.println("Ingrese id del producto a buscar: ");
		p.setId(Integer.parseInt(lector.nextLine()));
		Product prod = handler.search(p);
		if (prod!=null) {
			System.out.println(prod);
		}else {
			System.out.println("Producto no existe");
		}
	}
	
	public void newProd() {
		Product p = new Product();
		this.cargaprod(p);
		handler.newProduct(p);
		System.out.println("Añadido el producto: ");
		System.out.println(p);
	}
	
	public void update() {
		Product prod = new Product();
		System.out.println("Ingrese id de producto a actualizar: ");
		prod.setId(Integer.parseInt(lector.nextLine()));
		System.out.println("Datos actuales: ");
		System.out.println(handler.search(prod));
		
		this.cargaprod(prod);
		
		handler.updateProduct(prod);
	}
	
	public Product cargaprod(Product p) {
		System.out.println("Nombre: ");
		p.setName(lector.nextLine());
		System.out.println("Descripcion: ");
		p.setDescription(lector.nextLine());
		System.out.println("Precio: ");
		p.setPrice(Double.parseDouble(lector.nextLine()));
		System.out.println("Stock: ");
		p.setStock(Integer.parseInt(lector.nextLine()));
		System.out.println("Incluir shipping? Y/N");
		p.setShippingIncluded(lector.nextLine().trim().equalsIgnoreCase("Y"));
		return p;
	}
	
	public void delete() {
		Product p = new Product();
		System.out.println("ingrese id de producto a borrar: ");
		p.setId(Integer.parseInt(lector.nextLine()));
		handler.deleteProduct(p);
	}
}
