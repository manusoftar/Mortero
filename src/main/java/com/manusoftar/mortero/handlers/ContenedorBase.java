package com.manusoftar.mortero.handlers;

import java.util.LinkedList;
import java.util.List;

import com.manusoftar.mortero.gui.InventoryItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContenedorBase  extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	} 
	
	private Slot s1,s2,s3,s4;
	private InventoryItem i1,i2,i3,i4;
	
	public ContenedorBase(InventoryPlayer inventory){
		i1 = new InventoryItem();
		/*i2 = new InventoryItem();
		i3 = new InventoryItem();
		i4 = new InventoryItem();*/
		s1 = new Slot(i1, 0, 29, 16);
		s2 = new Slot(i1, 1, 29, 36);
		s3 = new Slot(i1, 2, 29, 56);
		s4 = new Slot(i1, 3, 119, 36);
		
		
		addSlotToContainer(s1);
		addSlotToContainer(s2);
		addSlotToContainer(s3);
		addSlotToContainer(s4);
		
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
}
