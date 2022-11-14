package proyecto.RedSocial.proyecto;

import proyecto.RedSocial.proyecto.model.Conection.MariaDBDatabase;

public class Exe {

	public static void main(String[] args) {
		try {
			MariaDBDatabase.create();
		} catch (Exception e) {
			System.out.println("Illo,..que la base datos ya esta creada");
		}

	}

}
