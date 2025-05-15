import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestLignes {

    public static void main(String[] args) {
        GestionTPG gtp = new GestionTPG();
        //System.out.println(gtp);
//        gtp.afficherSommeAchat();
//        gtp.afficherVehiculeMin();
        //gtp.lstLignes();

        // affichage lignes
        gtp.afficherLignes();

        /**************** 2 ****************/
        gtp.nbBusTrolleys();

        /**************** 3 ****************/
        gtp.afficherSommeAchat();

        System.out.println("-----------------------------------");
    }
}