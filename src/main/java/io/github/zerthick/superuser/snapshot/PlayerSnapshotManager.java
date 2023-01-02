package io.github.zerthick.superuser.snapshot;

import io.github.zerthick.superuser.SuperUser;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerSnapshotManager {

    private static Server server = Bukkit.getServer();
    private final List<String> enterCommands;
    private final List<String> exitCommands;
    private SuperUser instance;
    private Map<UUID, PlayerSnapshot> playerSnapshotMap;

    public PlayerSnapshotManager(SuperUser instance) {
        this.instance = instance;

        enterCommands = instance.getConfigurationSettings().enterCommands;
        exitCommands = instance.getConfigurationSettings().exitCommands;
        playerSnapshotMap = new HashMap<>();
    }

    public void createPlayerSnapshot(Player player) {
        playerSnapshotMap.put(player.getUniqueId(), new PlayerSnapshot(player));
        for (String enterCommand : enterCommands) {
            executeCmd(enterCommand, player);
        }
    }

    public void restorePlayerSnapshot(Player player) {
        PlayerSnapshot snapshot = playerSnapshotMap.remove(player.getUniqueId());
        for (String exitCommand : exitCommands) {
            executeCmd(exitCommand, player);
        }
        snapshot.restore();
    }

    public void restoreAllPlayerSnapshot() {
        playerSnapshotMap.forEach((k, v) -> restorePlayerSnapshot(Objects.requireNonNull(Bukkit.getPlayer(k))));
    }

    public boolean hasSnapshot(Player player) {
        return playerSnapshotMap.containsKey(player.getUniqueId());
    }

    private void executeCmd(String command, Player player) {
        String replacedCommand = command.replaceAll("%p", player.getName()).replaceAll("%w", player.getWorld().getName());
        server.dispatchCommand(server.getConsoleSender(), replacedCommand);
    }

}
