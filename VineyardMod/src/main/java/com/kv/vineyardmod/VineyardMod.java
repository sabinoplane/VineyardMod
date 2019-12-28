package com.kv.vineyardmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kv.vineyardmod.lists.BlockList;
import com.kv.vineyardmod.lists.ItemList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("vineyardmod")
public class VineyardMod {
	public static VineyardMod instance;
	public static final String modid = "vineyardmod";
	private static final Logger logger  = LogManager.getLogger(modid);
	
	
	public VineyardMod() {
		
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ClientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)	{
		logger.info("Setup method registered.");
		
	}
	
	private void ClientRegistries(final FMLClientSetupEvent event ) {
		logger.info("ClientRegistries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					ItemList.grapes = new Item(new Item.Properties().group(ItemGroup.FOOD)).setRegistryName(location("grapes")),
			
					ItemList.cask = new BlockItem(BlockList.cask, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(BlockList.cask.getRegistryName())
			);
			
			//Logger.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
					BlockList.cask = new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)).setRegistryName(location("Cask"))
			);
			
			//Logger.info("Items registered.");
		}
		
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(modid, name);
		}
	}
	
}
