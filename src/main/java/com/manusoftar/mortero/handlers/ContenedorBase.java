package com.manusoftar.mortero.handlers;

import java.util.LinkedList;
import java.util.List;

import com.manusoftar.mortero.gui.InventoryItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContenedorBase  extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	} 
	
	public static Slot[] slots;
	private InventoryItem i1;
	
	public ContenedorBase(InventoryPlayer inventory){
		i1 = new InventoryItem();
		/*i2 = new InventoryItem();
		i3 = new InventoryItem();
		i4 = new InventoryItem();*/
		slots = new Slot[4];
		slots[0] = new Slot(i1, 28, 30, 17);
		slots[1] = new Slot(i1, 29, 30, 38);
		slots[2] = new Slot(i1, 30, 30, 59);
		slots[3] = new Slot(i1, 31, 124, 34);
		
		for (Slot slot : slots){
			addSlotToContainer(slot);
		}
		
		bindPlayerInventory(inventory);
		
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
        }

        for (int i = 0; i < 9; i++) {
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}
	
	@Override 
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(index);	
		
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                /*if (slot < slots.length) {
                        if (!this.mergeItemStack(stackInSlot, tileEntity.getSizeInventory(), 36+tileEntity.getSizeInventory(), true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, tileEntity.getSizeInventory(), false)) {
                        return null;
                }*/

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(playerIn, stackInSlot);
        }
        return stack;
	}
	
	
}
