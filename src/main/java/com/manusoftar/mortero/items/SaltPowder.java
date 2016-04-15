package com.manusoftar.mortero.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SaltPowder extends Item {
	
	public static SaltPowder instance;
	
	public SaltPowder(){
		super();
		instance = this;
	}
	
	public SaltPowder(String unlocalizedname){
		super();
		this.setUnlocalizedName(unlocalizedname);
		this.setCreativeTab(CreativeTabs.tabMisc);
		
	}

}
