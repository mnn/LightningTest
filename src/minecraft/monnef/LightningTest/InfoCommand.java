package monnef.LightningTest;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

import java.lang.reflect.Field;

public class InfoCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "w";
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring) {
        Field f = ReflectionHelper.findField(World.class, "worldInfo");
        if ((icommandsender instanceof EntityPlayer)) {
            WorldInfo info = null;
            EntityPlayer player = (EntityPlayer) icommandsender;
            try {
                info = (WorldInfo) f.get(player.worldObj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            boolean dump = false;
            if (astring.length == 1) {
                String param = astring[0];
                if (param.equals("tt")) {
                    info.setThunderTime(2);
                    player.addChatMessage("setting thunder time");
                } else if (param.equals("rt")) {
                    info.setRainTime(2);
                    player.addChatMessage("setting rain time");
                } else {
                    player.addChatMessage("parameter not recognized");
                    dump = true;
                }
            } else {
                dump = true;
            }

            if (dump) player.addChatMessage(EventHandler.dump(player.worldObj, info, "command"));
        }
    }
}
