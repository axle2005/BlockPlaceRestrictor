package io.github.axle2005.blockplacerestrictor.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import io.github.axle2005.blockplacerestrictor.BlockPlaceRestrictor;
import io.github.axle2005.blockplacerestrictor.Config;



public class CommandPnbrAdd implements CommandExecutor {
	BlockPlaceRestrictor plugin;
	Config config;

	public CommandPnbrAdd(BlockPlaceRestrictor plugin,Config config) {
		this.plugin = plugin;
		this.config = config;
		
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {
		
		
		String block1 = arguments.<String>getOne("Block1").get();
		String block2 = arguments.<String>getOne("Block2").get();
		
		String commit = block1 +"|"+block2;
		
		if(!(plugin.listPnbrBlocks.contains(commit)))
		{
			plugin.listPnbrBlocks.add(commit);
			config.setValueList("PlaceNearBlockRestrictor", plugin.listPnbrBlocks);
			config.saveConfig();
			src.sendMessage(Text.of("Added Blocks to List"));
			return CommandResult.success();
		}
		else
		{
			src.sendMessage(Text.of("Blocks Already On The List"));
			return CommandResult.success();
		}
		
		
		

		
		
	}

}
