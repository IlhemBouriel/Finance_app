package tn.gl4.finance.util;

public enum Devise {

    THB("Bath Thailandais"),
    BOB("Boliviano Bolivien"),
    DKK("Couronne danoise"),
    ISK("Couronne islandaise"),
    NOK("Couronne norvégienne"),
    SEK("Couronne suédoise"),
    CZK("Couronne tchèque"),
    DZD("Dinar Algérien"),
    LYD("Dinar Lybien"),
    TND("Dinar Tunisien"),
    AED("Dirham Emirats Arabes Unis"),
    MAD("Dirham marocain"),
    USD("Dollar américain"),
    AUD("Dollar australien"),
    CAD("Dollar canadien"),
    HKD("Dollar hong-kongais"),
    NZD("Dollar néo-zélandais"),
    SGD("Dollar de Singapour"),
    TWD("Dollar taiwanais"),
    EUR("Euro"),
    HUF("Forint hongrois"),
    CHF("Franc Suisse"),
    GBP("Livre sterling"),
    TRY("Livre turque"),
    ARS("Peso Argentin"),
    COP("Peso colombien"),
    MXN("Peso mexicain"),
    PHP("Peso philippin"),
    ZAR("Rand sud-africain"),
    BRL("Real brésilien"),
    QAR("Rial du Qatar"),
    MYR("Ringgit Malais"),
    RUB("Rouble russe"),
    INR("Roupie indienne"),
    IDR("Roupie indonésienne"),
    PKR("Roupie pakistanaise"),
//    ILS("Shekel israélien"),
    KRW("Won sud coréen"),
    JPY("Yen Japonais"),
    CNY("Yuan chinois"),
    PLN("Zloty polonais");

    private String value;
    private String name;

    private Devise(String value) {
        this.value=value;
    }

    public String code(){
        return name() ;
    }

    public String toString(){
        return name();
    }

    public String fullName(){
        return value ;
    }

    public boolean equals(Devise other){
        if (other == null) return false ;
        else if (this==(other))
            return true;
        else return false ;
    }


}
