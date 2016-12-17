package io.github.axle2005.blockplacerestrictor.listeners;

import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Direction;

import io.github.axle2005.blockplacerestrictor.BlockPlaceRestrictor;

public class ListenerPlaceBlock implements EventListener<ChangeBlockEvent.Place> {

	BlockPlaceRestrictor plugin;

	public ListenerPlaceBlock(BlockPlaceRestrictor plugin) {
		this.plugin = plugin;

	}

	@Override
	public void handle(ChangeBlockEvent.Place event) throws Exception {
		List<String> list = plugin.listRestrictedBlocks;
		List<String> list2 = plugin.listPnbrBlocks;
		Boolean breakloop = false;
		if (!(event.getTransactions().isEmpty())) {
			BlockSnapshot block = event.getTransactions().get(0).getFinal();

			for (String s : list) {

				if (block.getState().getType().getId().toString().equals(s)
						&& event.getTargetWorld().equals(Sponge.getServer().getWorld("world").get())) {
					event.setCancelled(true);
					feedback(event.getCause(), "This can not be placed in this world");
					breakloop = true;
					break;
				}
			}
			if (breakloop == false) {
				for (String s : list2) {
					String[] pnbr = s.split("\\|");

					if (block.getState().getType().getId().toString().equals(pnbr[0])) {

						for (Direction d : Direction.values())
							if (d.isCardinal() || d.isUpright()) {

								if (block.getLocation().get().getBlockRelative(d).getBlockType().getId().toString()
										.equals(pnbr[1])) {
									event.setCancelled(true);
									feedback(event.getCause(), pnbr[0] + " & " + pnbr[1] + " can't be placed together");
									break;
								}

							}

					}
					if (block.getState().getType().getId().toString().equals(pnbr[1])) {

						for (Direction d : Direction.values())
							if (d.isCardinal() || d.isUpright()) {
								if (block.getLocation().get().getBlockRelative(d).getBlockType().getId().toString()
										.equals(pnbr[0])) {
									event.setCancelled(true);
									feedback(event.getCause(), pnbr[0] + " & " + pnbr[1] + " can't be placed together");
									break;
								}

							}
					}
				}
			}

		}

	}

	private void feedback(Cause cause, String message) {
		if (cause.first(Player.class).isPresent()) {
			Player player = cause.first(Player.class).get();

			player.sendMessage(Text.of(TextColors.RED, message));
		}
	}

}