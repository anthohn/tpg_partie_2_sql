import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestionTPG {
    ArrayList<Vehicule> lstVehicules;

    public GestionTPG() {
        this.lstVehicules = lireDonnees();
    }

    public int calculerSommeAchat() {
        int somme = 0;
        for (Vehicule vehicule : this.lstVehicules) {
            somme += vehicule.getPrixAchat();
        }
        return somme ;
    }

    public void afficherSommeAchat() {
        System.out.println("Le total de prix d'achat est : " + this.calculerSommeAchat());
    }

    public  Vehicule trouveVehiculeMin(){
        Vehicule min = this.lstVehicules.get(0);
        for (Vehicule vehicule : this.lstVehicules) {
            if(vehicule.getKmAuCompteur() < min.getKmAuCompteur()){
                min = vehicule;
            }
        }
        return min;
    }

    public void afficherVehiculeMin(){
        System.out.println("Le véhicule avec le moins de kil est " + this.trouveVehiculeMin());
    }

    public  ArrayList<Vehicule> lireDonnees() {
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

    @Override
    public String toString() {
        String affichage = "";
        for (Vehicule v : this.lstVehicules) {
            affichage += v + "\n";
        }
        return affichage;
    }
}