import java.time.LocalDate;


public class Trolley extends Vehicule {
    private int tensionNeccessaire; // nb de Volt nécessaires pour propulser le Trolley à pleine charge
    private boolean moteurThermique; // le Trolley est-il équipé d'un moteur thermique (Diesel) de secours?
    public Trolley(int nbPassagersMax, long prixAchat, LocalDate dateAcquisition, int tensionNeccessaire, boolean moteurThermique) {
        super(nbPassagersMax, prixAchat, dateAcquisition);
        this.tensionNeccessaire = tensionNeccessaire;
        this.moteurThermique = moteurThermique;
    }
}
