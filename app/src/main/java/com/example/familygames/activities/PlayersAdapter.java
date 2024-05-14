package com.example.familygames.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.familygames.R;
import com.example.familygames.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {
    private final List<Player> players;

    public Map<Player, Integer> getTemporarySelections() {
        return temporarySelections;
    }

    private Map<Player, Integer> temporarySelections = new HashMap<>();

    private final int level;

    public PlayersAdapter(List<Player> players, int level) {
        this.players = players;
        this.level = level;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.playerNameTextView.setText(player.getName());
        int playerScore = player.getScore();
        String resIdString = "level" + level + "_" + playerScore;
        int resId = holder.itemView.getContext().getResources().getIdentifier(resIdString, "drawable", holder.itemView.getContext().getPackageName());
        holder.playerLevelImageView.setImageResource(resId);

        holder.playerOptionsRadioGroup.setOnCheckedChangeListener(null);
        holder.playerOptionsRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            temporarySelections.remove(player);
            temporarySelections.put(player, checkedId-2*player.getId());
        });


        }

    @Override
    public int getItemCount() {
        return players.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerNameTextView;
        ImageView playerLevelImageView;
        RadioGroup playerOptionsRadioGroup;

        PlayerViewHolder(View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.playerNameTextView);
            playerLevelImageView = itemView.findViewById(R.id.playerLevelImageView);
            playerOptionsRadioGroup = itemView.findViewById(R.id.playerStatusRadioGroup); // Initialisation du RadioGroup
        }
    }
}
