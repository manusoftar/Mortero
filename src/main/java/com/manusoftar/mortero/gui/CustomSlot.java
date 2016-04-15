package com.manusoftar.mortero.gui;

import com.manusoftar.mortero.handlers.ContenedorBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class CustomSlot extends Slot {

	private boolean isOutputOnly = false;
	private ContenedorBase eventHandler;
	
	public CustomSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}

	public boolean isOutputOnly() {
		return isOutputOnly;
	}

	public void setEventHandler(ContenedorBase eHandler){
		eventHandler = eHandler;	
	}
	
	public void setOutputOnly(boolean isOutputOnly) {
		this.isOutputOnly = isOutputOnly;
	}

	@Override
	 /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return !isOutputOnly;
    }
	
	public boolean isEmpty(){
		   return !this.getHasStack();			
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
		//if (isOutputOnly){
			eventHandler.pickupFromSlot(stack,this.slotNumber);
		//}
        //this.onSlotChanged();
    }
	
		
	
}
