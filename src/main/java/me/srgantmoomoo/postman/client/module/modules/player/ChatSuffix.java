package me.srgantmoomoo.postman.client.module.modules.player;

import java.util.Arrays;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.api.event.events.PacketEvent;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.Reference;
import me.srgantmoomoo.postman.client.module.Category;
import me.srgantmoomoo.postman.client.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
	
	public ChatSuffix() {
		super ("chatSuffix", "adds postman suffix to all of ur chat msg's.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings();
	}

	@EventHandler
	private Listener<PacketEvent.Send> packetListener = new Listener<>(event -> {
		if (toggled) {
			if (event.getPacket() instanceof CPacketChatMessage) {
				for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@")) {
					if (((CPacketChatMessage) event.getPacket()).getMessage().startsWith(s)) return;
				}

				((CPacketChatMessage) event.getPacket()).getMessage().concat(" " + "\u23D0" + toUnicode(" " + Reference.NAME + " strong"));
			}
		}
	});

	// wut
	public String toUnicode(String s) {
		return s.toLowerCase()
				.replace("a", "\u1d00")
				.replace("b", "\u0299")
				.replace("c", "\u1d04")
				.replace("d", "\u1d05")
				.replace("e", "\u1d07")
				.replace("f", "\ua730")
				.replace("g", "\u0262")
				.replace("h", "\u029c")
				.replace("i", "\u026a")
				.replace("j", "\u1d0a")
				.replace("k", "\u1d0b")
				.replace("l", "\u029f")
				.replace("m", "\u1d0d")
				.replace("n", "\u0274")
				.replace("o", "\u1d0f")
				.replace("p", "\u1d18")
				.replace("q", "\u01eb")
				.replace("r", "\u0280")
				.replace("s", "\ua731")
				.replace("t", "\u1d1b")
				.replace("u", "\u1d1c")
				.replace("v", "\u1d20")
				.replace("w", "\u1d21")
				.replace("x", "\u02e3")
				.replace("y", "\u028f")
				.replace("z", "\u1d22");
	}
	
	public void onEnable() {
		Main.EVENT_BUS.subscribe(this);
	}
	
	public void onDisbale() {
		Main.EVENT_BUS.unsubscribe(this);
	}
}
