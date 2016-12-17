package io.github.axle2005.blockplacerestrictor.listeners;


import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.block.ChangeBlockEvent;

import io.github.axle2005.blockplacerestrictor.BlockPlaceRestrictor;




public class ListenersRegister {

	BlockPlaceRestrictor plugin;
	ListenerPlaceBlock place;

	public ListenersRegister(BlockPlaceRestrictor plugin)
	{
		this.plugin = plugin;
		place = new ListenerPlaceBlock(plugin);
		
	}
	public void registerEvent(String event)
	{
		
		if(event.equals("PlaceBlock"))
		{
			Sponge.getEventManager().registerListener(plugin,ChangeBlockEvent.Place.class, place);
		}
		
		
	}
	public void unregisterEvent(String event)
	{
		if(event.equals("PlaceBlock"))
		{
			Sponge.getEventManager().unregisterListeners(place);
		}
		
	}
}
