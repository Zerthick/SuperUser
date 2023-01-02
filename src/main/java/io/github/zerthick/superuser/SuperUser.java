package io.github.zerthick.superuser;

import io.github.zerthick.superuser.snapshot.PlayerSnapshotManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class SuperUser extends JavaPlugin {

    private Logger logger;
    private ConfigurationSettings configurationSettings;
    private PlayerSnapshotManager snapshotManager;

    @Override
    public void onEnable() {
        logger = getLogger();

        saveDefaultConfig();
        configurationSettings = new ConfigurationSettings(this.getConfig());

        snapshotManager = new PlayerSnapshotManager(this);

        // Plugin startup logic
        logger.info(String.format("%s version %s by %s enabled!", getDescription().getName(), getDescription().getVersion(), getDescription().getAuthors()));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("sudo") && sender instanceof Player player) {

            // Toggle snapshots for play on command execution
            if (snapshotManager.hasSnapshot(player)) {
                snapshotManager.restorePlayerSnapshot(player);
            } else {
                snapshotManager.createPlayerSnapshot(player);
            }

        }
        return false;
    }

    @Override
    public void onDisable() {

        // Restore all snapshots on disable
        snapshotManager.restoreAllPlayerSnapshot();
    }

    public ConfigurationSettings getConfigurationSettings() {
        return configurationSettings;
    }
}
