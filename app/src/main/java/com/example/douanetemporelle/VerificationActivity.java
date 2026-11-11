package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Activité 2: n'inspectiw l profil w na3mlou el verdict
public class VerificationActivity extends AppCompatActivity {
    // AppCompactActivity bch trodelna l classe ecran android

    // attribut nom bch nestaamlouh ba3d fl verdict
    private String nom;

    @Override
    // onCreate =démarrage de l'ecran
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // torbet l'activité bl layout xml mteeha
        setContentView(R.layout.activity_verification);

        // lehne bch nesta9blou les données
        // getIntent() traja3 el Intent elli lancélna el activité hedhi fl main
        //0 dans getIntExtra(, 0) hia valeur par défaut au cas ou ma 3anech clé .
        String m = ExtraKeys.MATRICULE;
        Intent recu = getIntent();
        nom = recu.getStringExtra(ExtraKeys.nomVoyageur(m));
        int naissance = recu.getIntExtra(ExtraKeys.anneeNaissance(m), 0);
        int destination = recu.getIntExtra(ExtraKeys.anneeDestination(m), 0);
        String motif = recu.getStringExtra(ExtraKeys.motif(m));

        // Age temporel = année de destination - année de naissance
        int ageTemporel = destination - naissance;

        // Njibou les vues mel layout bel id
        TextView tvNom = findViewById(R.id.tv_nom_gh);
        TextView tvAge = findViewById(R.id.tv_age_gh);
        TextView tvTrajet = findViewById(R.id.tv_trajet_gh);
        TextView tvMotif = findViewById(R.id.tv_motif_gh);
        Button btnApprouver = findViewById(R.id.btn_approuver_gh);
        Button btnRefuser = findViewById(R.id.btn_refuser_gh);

        // N3amrou el textes (el %1$s w %1$d fel strings.xml yetbadlou bel valeurs)
        tvNom.setText(getString(R.string.label_nom, nom));
        tvAge.setText(getString(R.string.label_age, ageTemporel));
        tvTrajet.setText(getString(R.string.label_trajet, naissance, destination));
        tvMotif.setText(getString(R.string.label_motif, motif));

        // Contrainte: ken el motif "Modification du passé" W el différence= nb premier
        // el bouton Approuver yetsaker (disabled) men el loul fel onCreate

        boolean modifPasse = getString(R.string.motif_modification).equals(motif);
        if (modifPasse && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // Approuver -> verdict true, Refuser -> verdict false
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // Tab3ath el verdict (booléen) w el nom lel activité 3 b Intent explicite
    private void envoyerVerdict(boolean approuve) {
        String m = ExtraKeys.MATRICULE;
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(ExtraKeys.verdict(m), approuve);
        intent.putExtra(ExtraKeys.nomVoyageur(m), nom);
        startActivity(intent);
    }

    // Test nombre premier
    private static boolean estPremier(int n) {
        if (n < 2) return false;
        // Nverifiw el diviseurs men 2 7atta racine carrée mta3 n (i*i <= n)
        for (int i = 2; (long) i * i <= n; i++) {
            // Ken n yetgasem 3ala i raw mahouch premier
            if (n % i == 0) return false;
        }
        return true;
    }
}