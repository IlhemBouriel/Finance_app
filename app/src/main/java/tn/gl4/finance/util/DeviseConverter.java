package tn.gl4.finance.util;

public class DeviseConverter {
    private float taux;
    private Devise source;
    private Devise cible;

    public DeviseConverter setTaux(Devise from, Devise to, float taux)
    {
        this.taux = taux ;
        source = from ;
        cible = to ;
        return this ;
    }

    public float convert (float montant){
        return montant*taux ;
    }

    public float convert (float montant, Devise from, Devise to) throws Exception{
        if(source == null || cible == null){
            throw new NullPointerException("Devises entrés nulles, vous devez obtenir les taux auparavent");
        }else if(source == from && cible==to){
            return montant*taux ;
        }else if(source == to && cible == from){
            return montant/taux;
        } else throw new Exception("Vous devez télécharger les nouveaux taux") ;
    }
}
