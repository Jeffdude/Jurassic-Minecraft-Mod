package jmm.gui;

import jmm.gui.inventory.ContainerTestingBlock;
import jmm.tileentity.TileTestingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileTestingBlock){
                return new ContainerTestingBlock(player.inventory, (TileTestingBlock) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileTestingBlock){
                return new ContainerTestingBlock(player.inventory, (TileTestingBlock) tileEntity);
        }
        return null;
    }

}
