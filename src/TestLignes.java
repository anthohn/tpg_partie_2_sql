import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestLignes {

    public static void main(String[] args) {
        GestionTPG gtp = new GestionTPG();

        // affichage lignes
        gtp.afficherLignes();

        /**************** 2 ****************/
        System.out.println("\n/**************** 2 ****************/\n");
        gtp.nbBusTrolleys();

        /**************** 3 ****************/
        System.out.println("\n/**************** 3 ****************/\n");
        gtp.afficherSommeAchat();

        /**************** 4 ****************/
        System.out.println("\n/**************** 4 ****************/\n");
        gtp.afficherVehiculesAbove300();

        /**************** 6 ****************/
        System.out.println("\n/**************** 6 ****************/\n");
        gtp.afficherVehiculeMoinsKm();

        System.out.println("-----------------------------------");
    }
}