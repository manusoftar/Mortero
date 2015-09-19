package com.manusoftar.mortero.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

public class InventoryItem implements IInventory {
	
	private ItemStack[] items;
	
	
	public InventoryItem(){
			items = new ItemStack[4];
	}
	
	@Override
	public String getCommandSenderName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && index <items.length){
			return items[index];
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		this.getStackInSlot(index).stackSize-=count;
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		// TODO Auto-generated method stub
		return getStackInSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		if (index>=0 && index < items.length){
			items[index]=stack;
		}
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
