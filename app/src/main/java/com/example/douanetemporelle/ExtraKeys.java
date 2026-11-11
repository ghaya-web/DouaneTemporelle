package com.example.douanetemporelle;

/**
 * hethi classe mta3 les clés mta3 les extras.
 * Kol clé tebda b matricule (e5er 4 ar9am mel carte d'étudiant).
 * El clé ma tetktebch tet3mal b méthode statique.
 */
public final class ExtraKeys {

    // E5er 4 ar9am mel carte cin ma lkitech num f carte d'etudiant mr
    public static final String MATRICULE = "8787";
    private ExtraKeys() { }

    // Méthode statique : te5ou el matricule w el suffixe w traja3 clé kemla

    public static String build(String matricule, String suffixe) {
        return matricule + "_" + suffixe;
    }

    // Les clés mta3 les données elli bch nab3thouhom bin les activités
    public static String nomVoyageur(String matricule)      { return build(matricule, "NOM_VOYAGEUR"); }
    public static String anneeNaissance(String matricule)   { return build(matricule, "ANNEE_NAISSANCE"); }
    public static String anneeDestination(String matricule) { return build(matricule, "ANNEE_DESTINATION"); }
    public static String motif(String matricule)            { return build(matricule, "MOTIF"); }
    public static String verdict(String matricule)          { return build(matricule, "VERDICT"); }
}
