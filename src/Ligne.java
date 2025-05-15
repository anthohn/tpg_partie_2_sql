import java.util.ArrayList;


public class Ligne {
    private String nom;
    private ArrayList<String> listeDesArrets;
    private ArrayList<Integer> listDureeEntreArrets;
    private boolean compatibleTrolley;
    private int tensionLigne;

    public Ligne(String nom, ArrayList<String> listeDesArrets, ArrayList<Integer> listDureeEntreArrets, boolean compatibleTrolley, int tensionLigne) {
        this.nom = nom;
        this.listeDesArrets = listeDesArrets;
        this.listDureeEntreArrets = listDureeEntreArrets;
        this.compatibleTrolley = compatibleTrolley;
        this.tensionLigne = tensionLigne;
    }

    @Override
    public String toString() {
        String resultat = "Ligne " + nom + " :\n";
        resultat += "Arrêts : \n";

        if (listeDesArrets.isEmpty()) {
            resultat += "Aucun arrêt spécifié\n";
        } else {
            for (int i = 0; i < listeDesArrets.size(); i++) {
                resultat += listeDesArrets.get(i) + ", ";
            }
        }
        resultat += "\n";

        resultat += "Durée entre arrêts : \n";

        if (listDureeEntreArrets.isEmpty()) {
            resultat += "Aucune durée spécifiée\n";
        } else {
            for (int i = 0; i < listDureeEntreArrets.size(); i++) {
                resultat += listDureeEntreArrets.get(i) + " min, ";
            }
        }

        resultat += "\n";

        resultat += "Compatible trolley : " + (compatibleTrolley ? "Oui" : "Non")  + "\n";
        resultat += "Tension ligne : " + (tensionLigne > 0 ? tensionLigne : "Non spécifié") + "\n";

        return resultat;
    }
}
