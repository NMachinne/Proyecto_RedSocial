module proyecto.RedSocial.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires java.xml.bind;

    opens proyecto.RedSocial.proyecto to javafx.fxml;
    opens proyecto.RedSocial.proyecto.model.Entity to javafx.base;
    opens proyecto.RedSocial.proyecto.model.Conection to java.xml.bind;
    exports proyecto.RedSocial.proyecto;
}
