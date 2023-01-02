package io.github.zerthick.superuser;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigurationSettings {

    public final List<String> enterCommands;
    public final List<String> exitCommands;

    public ConfigurationSettings(final FileConfiguration config) {
        this.enterCommands = config.getStringList("enterCommands");
        this.exitCommands = config.getStringList("exitCommands");
    }

}
