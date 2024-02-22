package com.example.familygames.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.familygames.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

public class SetPlayerValuesActivity extends AppCompatActivity {

    LinearLayout playersContainer;
    private ImageView currentSelectedImageView;
    private final ActivityResultLauncher<Intent> selectImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Uri selectedImageUri = result.getData().getData();
                            if (currentSelectedImageView != null) {
                                currentSelectedImageView.setImageURI(selectedImageUri);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_player_values);

        int nbPlayers = getIntent().getIntExtra("nbPlayers", 2); // Par défaut, 2 joueurs
        playersContainer = findViewById(R.id.playersContainerLayout);

        for (int i = 0; i < nbPlayers; i++) {
            MaterialCardView cardView = createPlayerCard();
            playersContainer.addView(cardView);
        }
        Button startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(v -> {
            // Récupérer les noms des joueurs
            for (int i = 0; i < playersContainer.getChildCount(); i++) {
                MaterialCardView cardView = (MaterialCardView) playersContainer.getChildAt(i);
                LinearLayout linearLayout = (LinearLayout) cardView.getChildAt(0);
                TextInputLayout textInputLayout = (TextInputLayout) linearLayout.getChildAt(0);
                EditText editText = (EditText) textInputLayout.getEditText();
                if (editText != null) {
                    String playerName = editText.getText().toString();
                    // Enregistrer le nom du joueur
                    //TODO
                }
            }
            // Démarrer le jeu
            //TODO
        });
    }

    @SuppressLint("SetTextI18n")
    private MaterialCardView createPlayerCard() {
        // Création du CardView
        MaterialCardView cardView = new MaterialCardView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(layoutParams);

        // Ajout d'un LinearLayout pour contenir les éléments
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Ajout d'un TextInputLayout pour le nom du joueur
        TextInputLayout textInputLayout = new TextInputLayout(this);
        EditText editText = new EditText(this);
        editText.setHint("Nom du joueur");
        textInputLayout.addView(editText);

        // Ajout d'un ImageView pour l'image de profil
        ImageView profileImageView = new ImageView(this);
        profileImageView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        profileImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        profileImageView.setImageResource(android.R.drawable.ic_menu_gallery); // Image par défaut

        // Ajout d'un bouton pour sélectionner l'image
        Button selectImageButton = new Button(this);
        selectImageButton.setText("Sélectionner une image");
        selectImageButton.setOnClickListener(v -> {
            currentSelectedImageView = profileImageView; // Mettre à jour la référence
            openGallery();
        });


        // Ajouter les éléments au LinearLayout
        linearLayout.addView(textInputLayout);
        linearLayout.addView(profileImageView);
        linearLayout.addView(selectImageButton);

        // Ajouter le LinearLayout au CardView
        cardView.addView(linearLayout);

        return cardView;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(intent);
    }
}