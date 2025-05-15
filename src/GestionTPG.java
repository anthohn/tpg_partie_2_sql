import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestionTPG {
    ArrayList<Vehicule> lstVehicules;
    ArrayList<Ligne> lstLignes;

    public GestionTPG() {
        this.lstVehicules = lireVehicules();
        this.lstLignes = lireLignes();
    }

    /**************** 2 ****************/
    public void nbBusTrolleys() {
        int cptBus = 0;
        int cptTrolleys = 0;

        for (Vehicule v: lstVehicules) {
            if (v instanceof Bus) {
                cptBus++;
            } else if (v instanceof Trolley) {
                cptTrolleys++;
            }
        }
        System.out.println("Il y'a " + cptBus + " bus");
        System.out.println("Il y'a " + cptTrolleys + " trolleys");
    }

    /**************** 3 ****************/
    public int calculerSommeAchat() {
        int somme = 0;
        for (Vehicule vehicule : this.lstVehicules) {
            somme += vehicule.getPrixAchat();
        }
        return somme ;
    }

    /**************** 3 ****************/
    public void afficherSommeAchat() {
        System.out.println("Le total de prix d'achat est : " + this.calculerSommeAchat() + " CHF");
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

    public void afficherLignes() {
        for (Ligne l: this.lstLignes) {
            System.out.println(l);
        }
    }

    public  ArrayList<Vehicule> lireVehicules() {
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

    public  ArrayList<Ligne> lireLignes() {
        String url = "jdbc:sqlite:tpg.sqlite";
        ArrayList<Ligne> lstLignesDB = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url)) {
            String queryLignes = "SELECT * FROM lignes";
            try (PreparedStatement stmtLignes = conn.prepareStatement(queryLignes)) {
                try (ResultSet rsLignes = stmtLignes.executeQuery()) {
                    while (rsLignes.next()) {

                        int idLigne = rsLignes.getInt("id");
                        int accepteTrolley = rsLignes.getInt("accepte_trolley");
                        String nomLigne = rsLignes.getString("id");
                        int tensionNecessaire = rsLignes.getInt("tension_necessaire");
                        boolean boolAccepteTrolley = false ;
                        if (accepteTrolley == 1) {
                            boolAccepteTrolley = true;
                        }

                        ArrayList<String> lstArrets = new ArrayList<>();
                        ArrayList<Integer> listDureeEntreArrets = new ArrayList<>();
                        String selectLigne = "SELECT * from arrets JOIN ligne_arret ON arrets.id = ligne_arret.arret_id WHERE ligne_id = " + idLigne;

                        try (PreparedStatement stmtLignes2 = conn.prepareStatement(selectLigne)) {
                            try (ResultSet rsLignes2 = stmtLignes2.executeQuery()) {
                                while (rsLignes2.next()) {
                                    String nomArret = rsLignes2.getString("nom");
                                    int minutes = rsLignes2.getInt("minutes");

                                    lstArrets.add(nomArret);
                                    listDureeEntreArrets.add(minutes);
                                }
                            }
                        }

                        Ligne l =  new Ligne(nomLigne, lstArrets, listDureeEntreArrets, boolAccepteTrolley, tensionNecessaire);
                        lstLignesDB.add(l);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstLignesDB;
    }
}