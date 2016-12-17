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

public class CommandBlockInfo implements CommandExecutor {
	BlockPlaceRestrictor plugin;

	public CommandBlockInfo(BlockPlaceRestrictor plugin) {
		this.plugin = plugin;

	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext arguments) throws CommandException {

		if (src instanceof Player) {
			src.sendMessage(
					Text.of(((Player) src).getItemInHand(HandTypes.MAIN_HAND).get().getItem().getId().toString()));

		}
		return CommandResult.success();

	}

}
