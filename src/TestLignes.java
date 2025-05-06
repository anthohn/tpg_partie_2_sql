import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLignes {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HashMap<String, String> hm = new HashMap<>();
        hm.put("prenom", "Jaqcue");
        hm.put("nom", "Truc");


        hm.put("prenom", "Jacques");

        for (String k : hm.keySet()) {
            System.out.println(k + " : " + hm.get(k));
        }

        System.out.println(hm.values());
    }


    public static ArrayList<Vehicule> lireDonnees() {
        String url = "jdbc:sqlite:tpg.sqlite";
        ArrayList<Vehicule> lstVehiculeDB = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url)) {
            String queryVehicules = "SELECT * FROM vehicules";
            try (PreparedStatement stmtVehicules = conn.prepareStatement(queryVehicules)) {
                try (ResultSet rsVehicules = stmtVehicules.executeQuery()) {
                    while (rsVehicules.next()) {
                        int prix = rsVehicules.getInt("prix_achat");
                        int nbPassagers = rsVehicules.getInt("nb_passagers");

                        String dateStr = rsVehicules.getString("date_acquisition");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate dateAchat = LocalDate.parse(dateStr,formatter);

                        int km = rsVehicules.getInt("km");
                        int tensionConso = rsVehicules.getInt("tension_conso");
                        String moteurSecours = rsVehicules.getString("moteur_secours");
                        String typeVehicule = rsVehicules.getString("type_vehicule");

                        boolean moteurSecoursB = false ;
                        if ("oui".equals(moteurSecours)) {
                            moteurSecoursB = true;
                        }

                        Vehicule v ;
                        if(typeVehicule.equals("trolley")){
                            v = new Trolley(nbPassagers, prix, dateAchat, tensionConso, moteurSecoursB);
                        }
                        else {
                            v = new Bus(nbPassagers, prix, dateAchat);
                        }

                        v.setKmAuCompteur(km);
                        lstVehiculeDB.add(v);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstVehiculeDB;
    }
}
