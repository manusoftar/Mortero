package com.manusoftar.mortero.bloques;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Azufre extends Block {
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer(){ return EnumWorldBlockLayer.SOLID; }
	
	@Override public boolean isOpaqueCube() { return true; }
	
	@Override public boolean isFullCube() { return true; }
	
	@Override public int getRenderType() { return 3; }
	
	public static Item dropsItem;
	
	public Azufre(Material materialIn, Item dropItem) {
		super(materialIn);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.setHarvestLevel("pickaxe", 2); //Se necesita pico de hierro
		this.setUnlocalizedName("azufre_block");
		this.setHardness(2.5f);
		
		
		
		dropsItem = dropItem;
	}
	   
	
}
