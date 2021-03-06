package monnef.LightningTest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.common.MinecraftForge;

import java.util.logging.Logger;

@Mod(modid = "lightning-test", name = "Lightning Test", version = "1.0")
public class LightningTest {
    public static Logger log;

    @Init
    public void Load(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        log = Logger.getLogger("LT");
        Logger parent = FMLLog.getLogger();
        log.setParent(parent);
        log.setUseParentHandlers(true);
    }

    @Mod.ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        // isn't there prettier way to do it, like CommandRegistry.register(new ...)?
        ((ServerCommandManager) (FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager())).registerCommand(new InfoCommand());
    }
}