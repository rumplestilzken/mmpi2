module mmpi.app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires itextpdf;
    requires org.json;

    opens com.rumplestilzken to javafx.fxml;

    exports com.rumplestilzken;
}