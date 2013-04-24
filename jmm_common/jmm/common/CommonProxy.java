package jmm.common;

import jmm.gui.inventory.ContainerTestingBlock;
import jmm.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        switch(ID) {
            case GuiIds.TESTING_BLOCK: {
                return new ContainerTestingBlock(player.inventory, world, x, y, z);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        switch(ID) {
            case GuiIds.TESTING_BLOCK: {
                return new ContainerTestingBlock(player.inventory, world, x, y, z);
            }
        }
        return null;
    }

}
