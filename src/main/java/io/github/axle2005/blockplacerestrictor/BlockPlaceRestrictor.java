package io.github.axle2005.blockplacerestrictor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import io.github.axle2005.blockplacerestrictor.commands.CommandRegister;
import io.github.axle2005.blockplacerestrictor.listeners.ListenersRegister;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "blockplacerestrictor", name = "BlockPlaceRestrictor")
public class BlockPlaceRestrictor {

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;

	public Config config;
	public List<String> listRestrictedBlocks = new ArrayList<String>();
	public List<String> listPnbrBlocks = new ArrayList<String>();

	ListenersRegister register;
	@Inject
	private Logger log;

	@Listener
	public void initialization(GamePreInitializationEvent event) {
		config = new Config(this, defaultConfig, configManager);
		listRestrictedBlocks = config.getStringlist("WorldRestrictor");
		listPnbrBlocks = config.getStringlist("PlaceNearBlockRestrictor");
	}

	@Listener
	public void initialization(GameInitializationEvent event) {
		new CommandRegister(this, config);
		register = new ListenersRegister(this);
		register.registerEvent("PlaceBlock");
	}

	public Logger getLogger() {
		return log;
	}

	public List<String> getList(String name) {
		if (name.equals("WorldRestrictor")) {
			return listRestrictedBlocks;
		} else
			return listRestrictedBlocks;
	}
}
