package jmm.gui.inventory;

import jmm.tileentity.TileTestingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerTestingBlock extends Container {
    
    protected TileTestingBlock tileEntity;
    
    public ContainerTestingBlock(InventoryPlayer inventory, World world, int x,
            int y, int z) {
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }
        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventory, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    addSlotToContainer(new Slot(tileEntity, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
    }
    
    public ContainerTestingBlock(InventoryPlayer inventory,
            TileTestingBlock tileEntity2) {
        tileEntity = tileEntity2;
        
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventory, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 44 + inventoryColumnIndex * 18, 104 + inventoryRowIndex * 18));
            }
        }
        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) {
            this.addSlotToContainer(new Slot(inventory, actionBarSlotIndex, 44 + actionBarSlotIndex * 18, 162));
        }

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                        addSlotToContainer(new Slot(tileEntity, j + i * 3, 62 + j * 18, 17 + i * 18));
                }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return tileEntity.isUseableByPlayer(entityplayer);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            //null checks and checks if the item can be stacked (maxStackSize > 1)
            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //merges the item into player inventory since its in the tileEntity
                    if (slot < 9) {
                            if (!this.mergeItemStack(stackInSlot, 9, 45, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }

}
