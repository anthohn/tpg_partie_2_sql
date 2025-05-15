import java.time.LocalDate;

public class Vehicule
{
    private int nbPassagersMax;
    private long prixAchat;
    private int kmAuCompteur;
    private LocalDate dateAcquisition;

    public void setKmAuCompteur(int kmAuCompteur) {
        if(kmAuCompteur > 0)
            this.kmAuCompteur = kmAuCompteur;
        else
            throw new IllegalArgumentException();
    }

    public int getNbPassagersMax()
    {
        return nbPassagersMax;
    }

    public long getPrixAchat() {
        return prixAchat;
    }

    public int getKmAuCompteur() {
        return kmAuCompteur;
    }

    public void setNbPassagersMax(int nbPassagersMax)
    {
        this.nbPassagersMax = nbPassagersMax;
    }

    public Vehicule(int nbPassagersMax, long prixAchat, LocalDate dateAcquisition)
    {
        this.nbPassagersMax = nbPassagersMax;
        this.prixAchat = prixAchat;
        this.dateAcquisition = dateAcquisition;
    }

    @Override
    public String toString()
    {
        return String.format("Véhicule (prix: CHF %d, nb. passagers max: %d) de %d km", prixAchat, nbPassagersMax, kmAuCompteur);
    }
}