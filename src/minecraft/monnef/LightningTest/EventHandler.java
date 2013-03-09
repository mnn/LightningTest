package monnef.LightningTest;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.LightningGeneratedEvent;

public class EventHandler {
	private static Random rand = new Random();

	@ForgeSubscribe
	public void HandleLightning(LightningGeneratedEvent event) {
		System.out.print(String.format("lightning at %d %d %d ~ ", event.x,
				event.y, event.z));
		boolean remove = rand.nextBoolean();
		int bId;

		if (remove) {
			bId = Block.cake.blockID;
			System.out.println("bolt removed");
			event.setResult(Result.DENY);
		} else {
			bId = Block.wood.blockID;
			System.out.println("bolt stays");
		}

		event.world.setBlock(event.x, event.y, event.z, bId);
	}
}