package io.github.axle2005.blockplacerestrictor.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import io.github.axle2005.blockplacerestrictor.BlockPlaceRestrictor;
import io.github.axle2005.blockplacerestrictor.Config;

public class CommandRegister {

	String name = "blockplacerestrictor";

	public CommandRegister(BlockPlaceRestrictor plugin, Config config) {
		CommandSpec placeadd = CommandSpec.builder().permission(name + ".add")
				.description(Text.of("Add Block to World Restrictor"))
				.arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("block|hand"))))
				.executor(new CommandPlaceAdd(plugin, config)).build();

		CommandSpec pnbradd = CommandSpec.builder().permission(name + ".add")
				.description(Text.of("Add Block to PlaceNearBlockRestrictor"))
				.arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("Block1"))),
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("Block2"))))
				.executor(new CommandPnbrAdd(plugin, config)).build();
		
		CommandSpec blockinfo = CommandSpec.builder().permission(name + ".add")
				.description(Text.of("Gets ID of block in hand"))
				.executor(new CommandBlockInfo(plugin)).build();

		CommandSpec blockplace = CommandSpec.builder().description(Text.of("BlockPlaceRestrictor Command"))
				.child(placeadd, "add").child(pnbradd, "pnbr").child(blockinfo, "info")
				// .child(dump, "dump").child(stats, "tps").child(reload,
				// "reload")
				// .child(add, "add")

				.build();

		Sponge.getCommandManager().register(plugin, blockplace, "blockplacerestrictor", "bpr");
	}
}