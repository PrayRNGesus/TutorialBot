package me.pray.tutorial.commands;

import java.awt.Color;

import me.pray.tutorial.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloCommand extends ListenerAdapter {

	EmbedBuilder helloEmbed = new EmbedBuilder().setColor(Color.BLUE).setTitle("Hello!").setDescription("Hello there!");
	EmbedBuilder reactionEmbed = new EmbedBuilder().setColor(Color.GREEN).setTitle("Thank you!")
			.setDescription("Thank you for reacting!");

	Long msgId;

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

		if (event.getMessage().getContentRaw().equalsIgnoreCase(Main.PREFIX + "hello")) {
			event.getChannel().sendMessage(helloEmbed.build()).queue(success -> {
				success.addReaction("a:verified:854396338279284776").queue();
				msgId = success.getIdLong();
			});
		}
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {

		if (event.getMessageIdLong() == msgId) {
			if (event.getUser().isBot()) {
				return;
			} else {
				event.getReaction().removeReaction(event.getUser()).queue();
				event.getChannel().sendMessage(reactionEmbed.build()).queue();
			}
		}

	}

}
