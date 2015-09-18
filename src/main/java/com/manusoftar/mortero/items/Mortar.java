package com.manusoftar.mortero.items;

import com.manusoftar.mortero.Mortero;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Mortar extends Item {
	public static Mortar instance;
	
	public Mortar(){
		super();
		instance = this;
	}
	
	public Mortar(String unlocalizedname){
		super();
		this.setUnlocalizedName(unlocalizedname);
		this.setCreativeTab(CreativeTabs.tabMisc);
		instance = this;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		      
		   playerIn.openGui(Mortero.instance, 0, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());		
		   return itemStackIn;
	}

}
