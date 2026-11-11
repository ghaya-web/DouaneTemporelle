package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

// Activité 3: el verdict le5reni a5dher = approuvé, a7mer = refusé
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Njibou el verdict w el nom elli jew mel activité 2
        String m = ExtraKeys.MATRICULE;
        Intent recu = getIntent();
        // false hia valeur par défaut ken el clé ma famech
        boolean approuve = recu.getBooleanExtra(ExtraKeys.verdict(m), false);
        String nom = recu.getStringExtra(ExtraKeys.nomVoyageur(m));

        LinearLayout root = findViewById(R.id.root_result_gh);
        TextView tvMessage = findViewById(R.id.tv_message_gh);
        Button btnNouveau = findViewById(R.id.btn_nouveau_gh);

        // Selon el verdict: nbadlou loun el fond w el message
        if (approuve) {
            root.setBackgroundColor(ContextCompat.getColor(this, R.color.verdict_approuve));
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            root.setBackgroundColor(ContextCompat.getColor(this, R.color.verdict_refuse));
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }

        // Bouton "Nouveau voyageur": nraj3ou lel MainActivity w nfasakhou el activités el 9dom
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);

            // CLEAR_TASK tfarregh el pile kemla, NEW_TASK tebdelna pile jdida
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            // finish() ysaker el activité eli mahloula taw
            finish();
        });
    }
}