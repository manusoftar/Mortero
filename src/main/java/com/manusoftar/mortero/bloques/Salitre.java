package com.manusoftar.mortero.bloques;

import java.util.ArrayList;

import com.manusoftar.mortero.Mortero;
import com.manusoftar.mortero.items.SalitreItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Salitre extends Block {

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer(){ return EnumWorldBlockLayer.SOLID; }
	
	@Override public boolean isOpaqueCube() { return true; }
	
	@Override public boolean isFullCube() { return true; }
	
	@Override public int getRenderType() { return 3; }
	
	public static Item dropsItem;
	
	public Salitre(Material materialIn, Item dropItem) {
		super(materialIn);
		this.setCreativeTab(CreativeTabs.tabBlock);
		// TODO Auto-generated constructor stub
		this.setHarvestLevel("pickaxe", 2); //Se necesita pico de hierro
		this.setUnlocalizedName("salitre_ore");
		this.setHardness(2.5f);
		dropsItem = dropItem;
	}
	   
	@Override
	public ArrayList getDrops(IBlockAccess world, BlockPos pos, IBlockState blockstate, int fortune){
		   ArrayList drops = new ArrayList();
		   drops.add(new ItemStack(dropsItem, (RANDOM.nextInt(3)+1)*(RANDOM.nextInt(fortune+1)+1)));
		   return drops;
	}
	
	

}
