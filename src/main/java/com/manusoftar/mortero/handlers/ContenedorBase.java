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
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContenedorBase  extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	} 
	
	public  CustomSlot[] slots;
	private InventoryItem i1;
	private static boolean crafted = false;
	
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
			slot.setEventHandler(this);
		}
		
		for (CustomSlot slot : slots){
			addSlotToContainer(slot);
		}
		
		bindPlayerInventory(inventory);
		
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inv){
		   //TODO Verificar si la configuración es válida para un crafteo, en caso afirmativo generar el item correspondiente	
		   boolean empty1, empty2, empty3;
		   empty1 = !slots[0].isEmpty();
		   empty2 = !slots[1].isEmpty();
		   empty3 = !slots[2].isEmpty();
		   //Primero checkeo si solo un slot tiene elementos, esto es para ver si tengo que craftear polvo de salitre
		   if (slots[3].isEmpty()){
			   
			   
			   boolean onlyone = (empty1 ^ empty2 ^ empty3) ^ (empty1 && empty2 && empty3);
			   /*System.out.println("Slot 0: " + !slots[0].isEmpty());
			   System.out.println("Slot 1: " + !slots[1].isEmpty());
			   System.out.println("Slot 2: " + !slots[2].isEmpty());
			   System.out.println("Solo Un Slot: " + onlyone);
			   */
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
					   aux.setItem(ClientProxy.spowder);
					   aux.stackSize = inv.getStackInSlot(slot).stackSize;
					   slots[3].putStack(aux);
					   //inv.clear();
				   }
			   } else if (empty1 && empty2 && empty3) {
				   // TODO verificar las cantidades de salitre, azufre y carbón y generar la cantidad de pólvora correspondiente
				   int coal,salt,sulfur;
				   coal = getCant(inv,Items.coal);
				   salt = getCant(inv,ClientProxy.spowder);
				   sulfur = getCant(inv,Item.getItemFromBlock(ClientProxy.azufre));
				   
				   if (salt>=15 && sulfur>=1 && coal>=3){
					   int cant = Math.min(sulfur, Math.min(salt/15, coal/3));
					   ItemStack result = new ItemStack(Items.gunpowder);
					   result.setItem(Items.gunpowder);
					   result.stackSize=cant*3;
					   slots[3].putStack(result);
				   }
			   }
			   
			   crafted = true;
		   } else {
			   /*System.out.println("El slot 3 no está vacío");
			   System.out.println("Slot 0: " + !empty1);
			   System.out.println("Slot 1: " + !empty2);
			   System.out.println("Slot 2: " + !empty3);*/
			   boolean onlyone = (empty1 ^ empty2 ^ empty3) ^ (empty1 && empty2 && empty3);
			   if (((!empty1 && !empty2 && !empty3) || !onlyone) && crafted){
				   System.out.println("Los tres slots de entrada están vacíos");
				   crafted=false;
				   slots[3].putStack(null);
			   }
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
		System.out.println("trasnferStackInSlot index: " + index);
        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (index < slots.length) {
                        if (!this.mergeItemStack(stackInSlot, slots[index].getStack().stackSize, Math.min(36+slots[index].getStack().stackSize,39), true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, slotObject.getStack().stackSize, false)) {
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
                slotObject.onPickupFromSlot(playerIn, stackInSlot);
        }
        return stack;
	}
	
	private int getCant(IInventory inv, Item item){
			//ItemStack aux = new ItemStack(item);
			//aux.setItem(item);
			for (CustomSlot cs : slots){
				if(cs.getStack()!=null){
					if (cs.getStack().getItem().equals(item)){
						return cs.getStack().stackSize;
					}
				}
			}
			return 0;
	}
	
	private int getSlotByItem(Item item){
		    int pos = 0;
		    for (CustomSlot slot : slots) {
		    	 if (slot.getHasStack()){
			    	 if (slot.getStack().getItem().equals(item)){
			    		 return slot.slotNumber;
			    	 }
		    	 }
		    }
		    return -1;
	}

	public void pickupFromSlot(ItemStack stack, int index) {
		   if (index==3){
			   ItemStack aux = new ItemStack(ClientProxy.spowder);
			   aux.setItem(ClientProxy.spowder);
			   ItemStack gpowder = new ItemStack(Items.gunpowder);
			   gpowder.setItem(Items.gunpowder);
			   
			   if (stack.getItem().equals(ClientProxy.spowder)){
				   int slot = 0;
				   int size = stack.stackSize;
				   ItemStack istack;
				   //Busco el slot que tiene el item
				   while((istack = i1.getStackInSlot(slot)) == null){
					   slot++;
				   }
				   if (slots[slot].getStack().stackSize>size){
					   slots[slot].getStack().stackSize-=size;
				   } else {
					   slots[slot].putStack(null);
				   }
				   slots[3].putStack(null);
			   } else {
				   System.out.println("Retirada/s " + slots[index].getStack().stackSize + " unidades de pólvora");
				   int coal,salt,sulfur;
				   coal = getSlotByItem(Items.coal);
				   salt = getSlotByItem(ClientProxy.spowder);
				   sulfur = getSlotByItem(Item.getItemFromBlock(ClientProxy.azufre));
				   
				   int sulfursize, saltsize, coalsize;
				   
				   sulfursize = slots[sulfur].getStack().stackSize;
				   saltsize = slots[salt].getStack().stackSize;
				   coalsize = slots[coal].getStack().stackSize;
				   
				   
				   sulfursize-=stack.stackSize/3;
				   saltsize-=stack.stackSize*5;
				   coalsize-=stack.stackSize;
				   
				   if (sulfursize<=0){
					   slots[sulfur].putStack(null);
				   } else {
					   slots[sulfur].getStack().stackSize=sulfursize;
				   }
				   
				   if (saltsize<=0){
					   slots[salt].putStack(null);
				   } else {
					   slots[salt].getStack().stackSize=saltsize;
				   }
				   
				   if (coalsize<=0){
					   slots[coal].putStack(null);
				   } else {
					   slots[coal].getStack().stackSize=coalsize;
				   } 
				   
				   
				   slots[3].putStack(null);
			   }
		   } else {
			   System.out.println("Retirado un item de un slot de entrada!");
			   slots[3].putStack(null);
		   }
	}
	
	
}
