package com.manusoftar.mortero.handlers;

import java.util.LinkedList;
import java.util.List;

import com.manusoftar.mortero.gui.CustomSlot;
import com.manusoftar.mortero.gui.InventoryItem;
import com.manusoftar.mortero.items.SalitreItem;
import com.manusoftar.mortero.items.SaltPowder;
import com.manusoftar.mortero.proxy.ClientProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContenedorBase  extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	} 
	
	public  CustomSlot[] slots;
	private InventoryItem i1;
	private boolean crafted = false;
	
	public ContenedorBase(EntityPlayer player){
		InventoryPlayer inventory = player.inventory;
		i1 = new InventoryItem(player.getHeldItem(),this);
		/*i2 = new InventoryItem();
		i3 = new InventoryItem();
		i4 = new InventoryItem();*/
		slots = new CustomSlot[4];
		slots[0] = new CustomSlot(i1, 0, 30, 17);
		slots[1] = new CustomSlot(i1, 1, 30, 38);
		slots[2] = new CustomSlot(i1, 2, 30, 59);
		slots[3] = new CustomSlot(i1, 3, 124, 35);
		
		slots[3].setOutputOnly(true);
		
		for (CustomSlot slot : slots){
			addSlotToContainer(slot);
		}
		
		bindPlayerInventory(inventory);
		
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inv){
		   //TODO Verificar si la configuración es válida para un crafteo, en caso afirmativo generar el item correspondiente	
		   
		   //Primero checkeo si solo un slot tiene elementos, esto es para ver si tengo que craftear polvo de salitre
		   if (!crafted){
			   boolean empty1, empty2, empty3;
			   empty1 = !slots[0].isEmpty();
			   empty2 = !slots[1].isEmpty();
			   empty3 = !slots[2].isEmpty();
			   
			   boolean onlyone = (empty1 ^ empty2 ^ empty3) ^ (empty1 && empty2 && empty3);
			   System.out.println("Slot 0: " + !slots[0].isEmpty());
			   System.out.println("Slot 1: " + !slots[1].isEmpty());
			   System.out.println("Slot 2: " + !slots[2].isEmpty());
			   System.out.println("Solo Un Slot: " + onlyone);
			   
			   /*System.out.println("Elemento en slot 0,0: " + slots[0].inventory.getStackInSlot(28));
			   System.out.println("Elemento en slot 0,1: " + slots[0].inventory.getStackInSlot(29));
			   System.out.println("Elemento en slot 0,2: " + slots[0].inventory.getStackInSlot(30));
			   System.out.println("Elemento en slot 0,3: " + slots[0].inventory.getStackInSlot(31));*/
			   
			   //Si se cumple que solo un slot entonces tengo que ver si lo que tiene es cristal de salitre
			   if (onlyone) {
				   int slot = 0;
				   ItemStack istack;
				   //Busco el slot que tiene el item
				   while((istack = inv.getStackInSlot(slot)) == null){
					   slot++;
				   }
				   
				   ItemStack aux1 = new ItemStack(SalitreItem.instance);
				   aux1.setItem(ClientProxy.sitem);
				   
				   if (inv.getStackInSlot(slot).isItemEqual(aux1)){
					   ItemStack aux = new ItemStack(SaltPowder.instance);
					   aux.stackSize = inv.getStackInSlot(slot).stackSize;
					   slots[3].inventory.setInventorySlotContents(3,aux);
					   //inv.clear();
				   }
			   }
			   crafted = true;
		   }
	}
	
	public void setCrafted(boolean crafted){
		this.crafted = crafted;
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
