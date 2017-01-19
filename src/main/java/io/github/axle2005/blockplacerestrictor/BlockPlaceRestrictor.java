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
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import io.github.axle2005.blockplacerestrictor.commands.CommandRegister;
import io.github.axle2005.blockplacerestrictor.listeners.ListenersRegister;
import me.ryanhamshire.griefprevention.GriefPrevention;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "blockplacerestrictor", name = "BlockPlaceRestrictor", dependencies = {
		@Dependency(id = "griefprevention", optional = true), })
public class BlockPlaceRestrictor {

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;

	private Config config;
	private List<String> listRestrictedBlocks = new ArrayList<String>();
	private List<String> listPnbrBlocks = new ArrayList<String>();
	private List<String> listClaim = new ArrayList<String>();
	
	private GriefPrevention griefPrevention;

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
	@Listener
	public void gameStarting(GameStartingServerEvent event) {

		try {
			Class.forName("me.ryanhamshire.griefprevention.GriefPrevention");
			griefPrevention = GriefPrevention.instance;
			getLogger().info("GriefPrevention Integration Successful!");
		} catch (ClassNotFoundException e) {
			getLogger().info("GriefPrevention Integration Failed!");
		}
		

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
	public GriefPrevention getGriefPrevention() {
		//GriefPrevention grief = GriefPrevention.instance;
		return griefPrevention;
	}
	public Config getConfig()
	{
		return config;
	}
	public List<String> getPnbrList(){
		return listPnbrBlocks;
	}
	public List<String> getRestrictedList(){
		return listRestrictedBlocks;
	}
	public List<String> getClaimList(){
		return listClaim;
	}
}
