package monnef.LightningTest;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "lightning-test", name = "Lightning Test", version = "1.0")
public class LightningTest {
	@Init
	public void Load(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
}