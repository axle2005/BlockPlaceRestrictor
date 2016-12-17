package io.github.axle2005.blockplacerestrictor.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.github.axle2005.blockplacerestrictor.BlockPlaceRestrictor;
import io.github.axle2005.blockplacerestrictor.Config;

public class CommandPlaceAdd implements CommandExecutor {
	BlockPlaceRestrictor plugin;
	Config config;

	public CommandPlaceAdd(BlockPlaceRestrictor plugin, Config config) {
		this.plugin = plugin;
		this.config = config;

	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {

		String args = arguments.getOne("block|hand").toString();

		if (args.equalsIgnoreCase("Optional[hand]")) {
			args = "hand";
		} else {
			args = args.substring(9, args.length() - 1);
		}
		if (args.equalsIgnoreCase("hand")) {
			if (src instanceof Player) {
				if (!(plugin.listRestrictedBlocks.contains(((Player) src).getItemInHand(HandTypes.MAIN_HAND).get()
						.getItem().getType().getId().toString()))) {
					plugin.listRestrictedBlocks.add(((Player) src).getItemInHand(HandTypes.MAIN_HAND).get().getItem()
							.getType().getId().toString());
					config.setValueList("WorldRestrictor", plugin.listRestrictedBlocks);
					config.saveConfig();
					src.sendMessage(Text.of("Added Block to List"));
				}

				return CommandResult.success();
			} else {
				src.sendMessage(Text.of("Only Players can run this command"));
				return CommandResult.empty();
			}
		} else {
			if (!(plugin.listRestrictedBlocks.contains(args))) {
				plugin.listRestrictedBlocks.add(args);
				config.setValueList("WorldRestrictor", plugin.listRestrictedBlocks);
				config.saveConfig();
				src.sendMessage(Text.of("Added Block to List"));
			}
			return CommandResult.empty();
		}

	}

}
