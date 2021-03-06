package com.manusoftar.mortero.proxy;

import java.util.LinkedList;
import java.util.List;

import com.manusoftar.mortero.Mortero;
import com.manusoftar.mortero.bloques.Azufre;
import com.manusoftar.mortero.bloques.Salitre;
import com.manusoftar.mortero.bloques.render.BlockRenderer;
import com.manusoftar.mortero.handlers.GuiHandler;
import com.manusoftar.mortero.items.Mortar;
import com.manusoftar.mortero.items.SalitreItem;
import com.manusoftar.mortero.items.SaltPowder;
import com.manusoftar.mortero.items.render.ItemRenderer;
import com.manusoftar.mortero.world.WorldGen;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {
boolean initDone = false;
	
	public static List<Block> blocks;
	public static SalitreItem sitem = new SalitreItem("salitre_crystal");;
	public static Mortar mortero = new Mortar("item_mortar");
	public static Salitre salitre = new Salitre(Material.rock, sitem);
	public static Azufre azufre = new Azufre(Material.rock, null);
	public static SaltPowder spowder = new SaltPowder("salt_powder");
	
	@Override
	public void init() {
		//if(initDone) return;
		//RenderingRegistry.registerEntityRenderingHandler(EntityCobble.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), GlobalAdditions.cobble_ball, Minecraft.getMinecraft().getRenderItem()));
		//RenderingRegistry.registerEntityRenderingHandler(EntityDodoEgg.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), GlobalAdditions.dodo_egg, Minecraft.getMinecraft().getRenderItem()));
		//RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), GlobalAdditions.explosive_ball, Minecraft.getMinecraft().getRenderItem()));
		//RenderingRegistry.registerEntityRenderingHandler(EntityRaptor.class, new RenderRaptor(new ModelRaptor(), 0.5F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityDodo.class, new RenderDodo(new ModelDodo(), 0.3F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityBrontosaurus.class, new RenderBrontosaurus(new ModelBrontosaurus(), 0.5f));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTranqAmmo.class, new RenderTranqAmmo());
		//RenderingRegistry.registerEntityRenderingHandler(EntityStoneSpear.class, new RenderStoneSpear());
		//RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, new RenderSpear());
		//ModelBakery.addVariantName(GlobalAdditions.slingshot, "arkcraft:slingshot", "arkcraft:slingshot_pulled");
		
		//proxy.init();
		//LogHelper.info("CommonProxy: Init run!");
		//initDone = true;
	}
	
	/* We register the block/item textures and models here */
	@Override
	public void registerRenderers() {
		/*for(Map.Entry<String, Block> e : GlobalAdditions.allBlocks.entrySet()) {
			String name = e.getKey();
			Block b = e.getValue();
			registerBlockTexture(b, name);
		}
		
		for(Map.Entry<String, Item> e : GlobalAdditions.allItems.entrySet()) {
			String name = e.getKey();
			Item item = e.getValue();
			registerItemTexture(item, name);
		}*/
		
		
    	 
    	
    	
    	
    	
    	
    	List<Item> items = new LinkedList<Item>();
    	blocks = new LinkedList<Block>();
    	
    	items.add(sitem);
    	items.add(mortero);
    	items.add(spowder);
    	blocks.add(salitre);
    	blocks.add(azufre);
    	
    	createBlocks(blocks);
    	createItems(items);

    	/*BlockRenderer.registerBlockRenderer(salitre);
    	BlockRenderer.registerBlockRenderer(azufre);
    	ItemRenderer.registerItemRenderer(sitem);
    	ItemRenderer.registerItemRenderer(mortero);*/
    	
    	registerBlocksTextures(blocks);
    	registerItemsTextures(items);
    	
		
	}
	
	@Override
	public void registerWorldGen(){
		   //for (Block block : blocks){	
			   GameRegistry.registerWorldGenerator(new WorldGen(blocks), 0);
		   //}
	}
	
	private static void createItems(List<Item> items) {
		// TODO Auto-generated method stub
		for (Item item : items) {
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		}
	}
	
	public static void createBlocks(List<Block> blocks){
    	for (Block block : blocks) {
    		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
    	}
    }
	
	public void registerBlocksTextures(final List<Block> blocks){
		   for (Block block : blocks) {
			   registerBlockTexture(block, block.getUnlocalizedName().substring(5));
		   }
	}
	
	public void registerItemsTextures(final List<Item> items){
		   for (Item item : items){
			   registerItemTexture(item, item.getUnlocalizedName().substring(5));
		   }
	}
	
	public void registerBlockTexture(final Block block, final String blockName) {
		registerBlockTexture(block, 0, blockName);
	}
	
	public void registerBlockTexture(final Block block, int meta, final String blockName) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Mortero.MODID + ":" + blockName, "inventory"));
	}
	
	public void registerItemTexture(final Item item, final String name) {
		registerItemTexture(item, 0, name);
	}
	
	public void registerItemTexture(final Item item, int meta, final String name) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(Mortero.MODID + ":" + name, "inventory"));
        //ModelBakery.addVariantName(item, Mortero.MODID + ":" + name);
	}
}
