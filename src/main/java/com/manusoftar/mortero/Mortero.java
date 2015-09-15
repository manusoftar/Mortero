package com.manusoftar.mortero;

import com.manusoftar.mortero.bloques.Salitre;
import com.manusoftar.mortero.bloques.render.BlockRenderer;
import com.manusoftar.mortero.items.SalitreItem;
import com.manusoftar.mortero.items.render.ItemRenderer;
import com.manusoftar.mortero.proxy.CommonProxy;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Mortero.MODID, version = Mortero.VERSION)
public class Mortero {
	
	@SidedProxy(clientSide="com.manusoftar.mortero.proxy.ClientProxy", serverSide="com.manusoftar.mortero.proxy.CommonProxy")
	
    public static final String MODID = "mortero";
    public static final String VERSION = "1.0";
    public static CommonProxy proxy;
    public static Mortero instance;
    
    
    public static Salitre salitre;
    public static SalitreItem sitem;
	

    
    
    
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        //System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    	
    	
    	proxy.registerRenderers();
    }



	
}
