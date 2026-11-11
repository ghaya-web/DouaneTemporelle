package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Activité 1: el formulaire win el voyageur y7ot les infos mteeou

public class MainActivity extends AppCompatActivity {

    // Les vues elli bch nesta3mlouhom fel code
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Norbtou  el activité bel layout XML mte3ha
        setContentView(R.layout.activity_main);

        // Njibou les vues mel layout bel id mte3hom (kol id fih les initiales )
        etNom = findViewById(R.id.et_nom_gh);
        etNaissance = findViewById(R.id.et_annee_naissance_gh);
        etDestination = findViewById(R.id.et_annee_destination_gh);
        rgMotif = findViewById(R.id.rg_motif_gh);
        Button btnInspecter = findViewById(R.id.btn_inspecter_gh);

        // Ki el user yenzel 3al bouton, ysir appel lel méthode inspecter()
        btnInspecter.setOnClickListener(v -> inspecter());
    }

    // Tverifi les données w tab3athhom lel activité 2
    private void inspecter() {
        // Ne5dhou el texte mel champs (trim() tfassa5 les espaces el zeydin )
        String nom = etNom.getText().toString().trim();
        String sNaissance = etNaissance.getText().toString().trim();
        String sDestination = etDestination.getText().toString().trim();

        // Ken user ma yhotech nom tsir erreur w to5rej
        if (nom.isEmpty()) {
            etNom.setError(getString(R.string.err_nom));
            return;
        }
        if (sNaissance.isEmpty()) {
            etNaissance.setError(getString(R.string.err_annee));
            return;
        }
        if (sDestination.isEmpty()) {
            etDestination.setError(getString(R.string.err_annee));
            return;
        }

        // Nconvertiw el textes l entiers (try/catch sta3mlneha au cas ou el valeur invalide )
        int naissance, destination;
        try {
            naissance = Integer.parseInt(sNaissance);
            destination = Integer.parseInt(sDestination);
        } catch (NumberFormatException e) {
            etNaissance.setError(getString(R.string.err_annee));
            return;
        }

        // L'année de destination lezem tkoun >= mel année de naissance
        if (destination < naissance) {
            etDestination.setError(getString(R.string.err_ordre));
            return;
        }

        // lenna nchoufou enehi radio button eli 5tarha user  (-1 ya3ni ma 5tar 7atta bouton donc tatla3lou toast fel ecran )
        int checkedId = rgMotif.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this, R.string.err_motif, Toast.LENGTH_SHORT).show();
            return;
        }
        // Njibou el texte mta3 el motif eli 5tarou l user
        String motif = ((RadioButton) findViewById(checkedId)).getText().toString();

        // Intent explicite: n7adou l classe mtaa l'activité eli bch tetlanca
        // (VerificationActivity)
        String m = ExtraKeys.MATRICULE;
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // putExtra tzid donnée lel intent sous forme  (clé, valeur).
        // Les clés houma eli sna3nehom fl  ExtraKeys à partir mel matricule .

        intent.putExtra(ExtraKeys.nomVoyageur(m), nom);
        intent.putExtra(ExtraKeys.anneeNaissance(m), naissance);
        intent.putExtra(ExtraKeys.anneeDestination(m), destination);
        intent.putExtra(ExtraKeys.motif(m), motif);
        // tlancilna  el activité 2
        startActivity(intent);
    }
}