package com.manusoftar.mortero.gui;

import com.manusoftar.mortero.items.Mortar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.Constants.NBT;

public class InventoryItem implements IInventory {
	
	private final ItemStack invItem;
	
	private ItemStack[] items;
	
	
	
	public InventoryItem(ItemStack stack){
		
		items = new ItemStack[4];
		// Create a new NBT Tag Compound if one doesn't already exist, or you will crash
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		invItem = stack;
		// note that it's okay to use stack instead of invItem right there
		// both reference the same memory location, so whatever you change using
		// either reference will change in the other

		// Read the inventory contents from NBT
		readFromNBT(stack.getTagCompound());
			
			
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
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		
		//System.out.println("getStackInSlot con index: " + index);
		
		if (index >= 28 && index < 32) {
			if (items[index-28]!=null){
				return items[index-28];
			} else { 
				return null;
			}
		}
		
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		ItemStack stack = getStackInSlot(index);
		if(stack != null)
		{
			if(stack.stackSize > count)
			{
				stack = stack.splitStack(count);
				// Don't forget this line or your inventory will not be saved!
				markDirty();
			}
			else
			{
				// this method also calls onInventoryChanged, so we don't need to call it again
				setInventorySlotContents(index, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		// TODO Auto-generated method stub
		//return getStackInSlot(index);
		ItemStack stack = getStackInSlot(index);
		setInventorySlotContents(index, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		//invItem = stack;
		if (index >= 28 && index < 32){
			if (items[index-28]==null){
				items[index-28]=stack;
			} else {
				if (stack!=null){
					items[index-28].stackSize+=stack.stackSize;
				}
			}
		}
		
		markDirty();
		//System.out.println("setInventorySlotContents con index: " + index);
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				items[i] = null;
			}
		}
		
		// This line here does the work:
		/*if (invItem.getTagCompound()==null){
			invItem.setTagCompound(new NBTTagCompound());
		}*/
		writeToNBT(invItem.getTagCompound());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		readFromNBT(invItem.getTagCompound());
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		//player.inventory.
		markDirty();
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return !(stack.getItem() instanceof Mortar);
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

	
	/**
	 * A custom method to read our inventory from an ItemStack's NBT compound
	 */
	public void readFromNBT(NBTTagCompound compound)
	{
		// Gets the custom taglist we wrote to this compound, if any
		// 1.7.2+ change to compound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		NBTTagList itemstag = compound.getTagList("ItemInventory", NBT.TAG_COMPOUND);

		for (int i = 0; i < itemstag.tagCount(); ++i)
		{
			// 1.7.2+ change to items.getCompoundTagAt(i)
			NBTTagCompound item = (NBTTagCompound) itemstag.getCompoundTagAt(i);
			int slot = item.getInteger("Slot");

			// Just double-checking that the saved slot index is within our inventory array bounds
			if (slot >= 0 && slot < getSizeInventory()) {
				items[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}
	
	
	/**
	 * A custom method to write our inventory to an ItemStack's NBT compound
	 */
	public void writeToNBT(NBTTagCompound tagcompound)	{
		// Create a new NBT Tag List to store itemstacks as NBT Tags
		NBTTagList itemsTag = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			// Only write stacks that contain items
			if (getStackInSlot(i) != null)
			{
				// Make a new NBT Tag Compound to write the itemstack and slot index to
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				// Writes the itemstack in slot(i) to the Tag Compound we just made
				getStackInSlot(i).writeToNBT(item);

				// add the tag compound to our tag list
				itemsTag.appendTag(item);
			}
		}
		// Add the TagList to the ItemStack's Tag Compound with the name "ItemInventory"
		tagcompound.setTag("ItemInventory", itemsTag);
	}
	
	
	
}
