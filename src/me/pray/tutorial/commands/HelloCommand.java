package me.pray.tutorial.commands;

import me.pray.tutorial.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloCommand extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(event.getMessage().getContentRaw().equalsIgnoreCase(Main.PREFIX + "hello")) {
			event.getChannel().sendMessage("hello!").queue();
		}
	}
	
}
