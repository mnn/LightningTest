package monnef.LightningTest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WeatherEvent;

import java.util.Random;

import static net.minecraftforge.event.world.WeatherEvent.LightningGeneratedEvent;

public class EventHandler {
    private static Random rand = new Random();

    @ForgeSubscribe
    public void handleLightning(LightningGeneratedEvent event) {
        System.out.print(String.format("lightning at %d %d %d ~ ", event.x,
                event.y, event.z));
        boolean remove = rand.nextBoolean();
        int bId;

        if (remove) {
            bId = Block.cake.blockID;
            System.out.println("bolt removed");
            event.setResult(Result.DENY);
        } else {
            bId = Block.obsidian.blockID;
            System.out.println("bolt stays");
        }

        event.world.setBlockAndMetadataWithNotify(event.x, event.y, event.z, bId, 0, 3);
        dump(event);
    }

    @ForgeSubscribe
    public void handleWeather(WeatherEvent.RainChangedEvent event) {
        dump(event);
    }

    @ForgeSubscribe
    public void handleWeather(WeatherEvent.ThunderingChangedEvent event) {
        dump(event);
    }

    @ForgeSubscribe
    public void handleWeather(WeatherEvent.StormChangedEvent event) {
        dump(event);
    }

    private static void dump(WeatherEvent event) {
        dump(event.world, event.info, event.getClass().getSimpleName());
    }

    public static String dump(World w, WorldInfo info, String msg) {
        String text = String.format("%s %s: ", msg, FMLCommonHandler.instance().getSide() == Side.CLIENT ? "C" : "S");

        if (w != null) {
            text += String.format("W[%s ts:%f rs:%f it:%s ir:%s ]", w.isRemote ? "C" : "S",
                    w.thunderingStrength, w.rainingStrength, w.isThundering(), w.isRaining());
        }

        if (info != null) {
            text += String.format(" WI[ tt:%d rt:%d it:%s ir:%s ]", info.getThunderTime(), info.getRainTime(), info.isThundering(), info.isRaining());
        }

        LightningTest.log.info(text);

        return text;
    }
}