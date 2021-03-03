package me.srgantmoomoo.postman.client.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.api.event.events.RenderEvent;
import me.srgantmoomoo.postman.api.util.render.Esp2dHelper;
import me.srgantmoomoo.postman.api.util.render.JTessellator;
import me.srgantmoomoo.postman.client.module.modules.client.*;
import me.srgantmoomoo.postman.client.module.modules.exploits.*;
import me.srgantmoomoo.postman.client.module.modules.hud.ArmorHud;
import me.srgantmoomoo.postman.client.module.modules.hud.ArrayListt;
import me.srgantmoomoo.postman.client.module.modules.hud.AutoCInfo;
import me.srgantmoomoo.postman.client.module.modules.hud.Coords;
import me.srgantmoomoo.postman.client.module.modules.hud.Frames;
import me.srgantmoomoo.postman.client.module.modules.hud.InventoryViewer;
import me.srgantmoomoo.postman.client.module.modules.hud.KillAuraInfo;
import me.srgantmoomoo.postman.client.module.modules.hud.Ping;
import me.srgantmoomoo.postman.client.module.modules.hud.SurroundInfo;
import me.srgantmoomoo.postman.client.module.modules.hud.Totems;
import me.srgantmoomoo.postman.client.module.modules.hud.Watermark;
import me.srgantmoomoo.postman.client.module.modules.movement.*;
import me.srgantmoomoo.postman.client.module.modules.player.*;
import me.srgantmoomoo.postman.client.module.modules.pvp.*;
import me.srgantmoomoo.postman.client.module.modules.render.*;
import me.srgantmoomoo.postman.client.ui.TabGui;
import me.srgantmoomoo.postman.client.ui.clickgui.ClickGuiModule;
import me.srgantmoomoo.postman.client.ui.clickgui.HudEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/*
 * Written by @SrgantMooMoo 11/17/20.
 */

public class ModuleManager {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		MinecraftForge.EVENT_BUS.register(this);
		modules = new ArrayList<>();
		
		//alphabetic
		modules.add(new AimBot());
		modules.add(new AntiHunger());
		modules.add(new AntiNick());
		modules.add(new AntiSwing());
		modules.add(new AutoArmor());
		modules.add(new AutoClicker());
		modules.add(new AutoCopeAndSeethe());
		modules.add(new AutoCrystal());
		modules.add(new AutoDisconnect());
		modules.add(new AutoElytra());
		modules.add(new AutoGap());
		modules.add(new AutoHut());
		modules.add(new AutoMine());
		modules.add(new AutoReconnect());
		modules.add(new AutoRespawn());
		modules.add(new AutoTotem());
		modules.add(new AutoTrap());
		modules.add(new AutoUse());
		modules.add(new AutoWalk());
		modules.add(new Backdoor2b2t());
		modules.add(new Blink());
		modules.add(new CameraClip());
		modules.add(new ChatBot());
		modules.add(new ChatSuffix());
		modules.add(new ChestStealer());
		modules.add(new CoordExploit());
		modules.add(new CraftingSlots());
		modules.add(new Criticals());
		modules.add(new DamageTiltCorrection());
		modules.add(new DeathCoords());
		modules.add(new Dupe());
		modules.add(new ElytraFly());
		modules.add(new Esp());
		modules.add(new FastUse());
		modules.add(new FootExp());
		modules.add(new Freecam());
		modules.add(new FullBright());
		modules.add(new HoleEsp());
		modules.add(new HoleTp());
		modules.add(new InventoryMove());
		modules.add(new Jesus());
		modules.add(new KillAura());
		modules.add(new LiquidPlace());
		modules.add(new LogOutSpot());
		modules.add(new LongJump());
		modules.add(new LowOffHand());
		modules.add(new Multitask());
		modules.add(new Nametags());
		modules.add(new NewChunks());
		modules.add(new NoFall());
		modules.add(new NoHandShake());
		modules.add(new NoPush());
		modules.add(new NoRender());
		modules.add(new NoSlow());
		modules.add(new Peek());
		modules.add(new PlayerClone());
		modules.add(new PortalGodMode());
		modules.add(new ReverseStep());
		modules.add(new SafeWalk());
		modules.add(new Scaffold());
		modules.add(new SmartOffHand());
		modules.add(new Sneak());
		modules.add(new Speed());
		modules.add(new Sprint());
		modules.add(new Step());
		modules.add(new Surround());
		modules.add(new Timer());
		modules.add(new Tracers());
		modules.add(new Velocity());
		modules.add(new ViewModel());
		modules.add(new Xray());
		//hud
		modules.add(new Watermark());
		modules.add(new Totems());
		modules.add(new Ping());
		modules.add(new Frames());
		modules.add(new AutoCInfo());
		modules.add(new KillAuraInfo());
		modules.add(new SurroundInfo());
		modules.add(new ArrayListt());
		modules.add(new InventoryViewer());
		modules.add(new Coords());
		modules.add(new ArmorHud());
		modules.add(new HudEditor());
		//client
		//ModuleManager.modules.add(new KeyStrokes());
		modules.add(new ClientFont());
		modules.add(new Capes());
		modules.add(new DiscordRichPresence());
		modules.add(new ClickGuiModule());
	 	modules.add(new TabGui());
	 	modules.add(new MainMenuInfo());
		modules.add(new Esp2dHelper());

		modules.sort(this::compareTo);
	}

	private int compareTo(Module mod1, Module mod2) {
		return mod1.getName().compareTo(mod2.getName());
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
	}
	
	public static void onRender() {
		modules.stream().filter(Module::isToggled).forEach(Module::onRender);
		Main.getInstance().clickGui.render();
	}
	
	public static void onWorldRender(RenderWorldLastEvent event) {
		Minecraft.getMinecraft().profiler.startSection("postman");
		Minecraft.getMinecraft().profiler.startSection("setup");
		JTessellator.prepare();
		RenderEvent e = new RenderEvent(event.getPartialTicks());
		Minecraft.getMinecraft().profiler.endSection();

		modules.stream().filter(Module::isToggled).forEach(module -> {
			Minecraft.getMinecraft().profiler.startSection(module.getName());
			module.onWorldRender(e);
			Minecraft.getMinecraft().profiler.endSection();
		});

		Minecraft.getMinecraft().profiler.startSection("release");
		JTessellator.release();
		Minecraft.getMinecraft().profiler.endSection();
		Minecraft.getMinecraft().profiler.endSection();
	}
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
			return;
		try {
			if(Keyboard.isCreated()) {
				if(Keyboard.getEventKeyState()) {
					int keyCode = Keyboard.getEventKey();
					if(keyCode <= 0)
						return;
					for(Module m : ModuleManager.modules) {
						if(m.getKey() == keyCode) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) { q.printStackTrace(); }
	}
	
	public static void addChatMessage(String message) {
		message = ChatFormatting.AQUA + "@" + ChatFormatting.ITALIC + Reference.NAME + ChatFormatting.GRAY + ": " + message;
		Minecraft.getMinecraft().player.sendMessage(new TextComponentString(message));
	}
	
	public static boolean isModuleEnabled(String name){
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		assert m != null;
		return m.isToggled();
	}
	
	public Module getModule (String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public ArrayList<Module> getModuleList() {
		return ModuleManager.modules;
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : ModuleManager.modules) {
			if(!m.getName().equals("Esp2dHelper")) {
			if(m.getCategory() == c)
				modules.add(m);
		}
		}
		return modules;
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static ArrayList<Module> getModulesInCategory(Category c){
		return (ArrayList<Module>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
	}
	
	public static Module getModuleByName(String name){
		return modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
}
