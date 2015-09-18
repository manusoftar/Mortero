package com.manusoftar.mortero.handlers;

import com.manusoftar.mortero.gui.InventoryItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContenedorBase  extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	} 
	
	private Slot s1,s2,s3,s4;
	
	public ContenedorBase(){
		s1 = new Slot(new InventoryItem(), 0, 29, 16);
		s2 = new Slot(new InventoryItem(), 1, 29, 36);
		s3 = new Slot(new InventoryItem(), 2, 29, 77);
		s4 = new Slot(new InventoryItem(), 3, 119, 30);
		
		addSlotToContainer(s1);
		addSlotToContainer(s2);
		addSlotToContainer(s3);
		addSlotToContainer(s4);
	}
}
