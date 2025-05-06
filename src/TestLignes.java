import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestLignes {

    public static void main(String[] args) {
        GestionTPG gtp = new GestionTPG();
        System.out.println(gtp);
        gtp.afficherSommeAchat();
        gtp.afficherVehiculeMin();

        System.out.println("-----------------------------------");
    }
}