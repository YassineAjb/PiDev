module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    requires com.dlsc.formsfx;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    requires java.sql;
    opens model;
    requires jbcrypt;
    requires org.apache.pdfbox;
    requires java.mail;
    requires atlantafx.base;
    requires twilio;

    exports utils;
    opens utils to javafx.fxml;

}