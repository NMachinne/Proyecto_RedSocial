package proyecto.Postt.com;

import proyecto.Postt.com.model.Conection.MariaDBDatabase;

public class Test {
	public static void main(String[] args) {
		
		try {
			MariaDBDatabase.create();
		} catch (Exception e) {
			System.out.println("Illo,..que la base datos ya esta creada");
		}
		
	}
}
