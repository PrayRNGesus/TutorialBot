package me.pray.tutorial.commands;

import me.pray.tutorial.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanCommand extends ListenerAdapter {

	/*
	 * Episode 4
	 * Challenge: Find out why the ban with no reason doesn't work!
	 * Add me on discord for help, @Pray#0001 or join the JDA official discord
	 * */
	
	
	private Main main;

	public BanCommand(Main main) {
		this.main = main;
	}

	String banReason = "";

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String content = event.getMessage().getContentRaw();
		String[] args = content.split("\\s+");

		if (content.startsWith(main.prefix + "ban")) {
			banReason = "";

			// minimum of 2 arguments, because <ban [user]>
			if (args.length >= 2) {
				if (args.length == 2) {
					banReason = "No reason provided";
				} else {
					for (int i = 2; i < args.length; i++) {
						banReason += args[i] + " ";
					}

					Member memberToBan = event.getMessage().getMentionedMembers().get(0);
					User mod = event.getAuthor();
					String modMention = mod.getAsMention();

					// getting the moderator (by id)
					event.getGuild().retrieveMemberById(mod.getIdLong()).queue(user -> {
						// checking if the moderator is admin, if not restricting their access to use
						// the command
						if (user.hasPermission(Permission.ADMINISTRATOR)) {
							// getting the member who you want to ban (by id)
							event.getGuild().retrieveMemberById(memberToBan.getIdLong()).queue(whoToBan -> {
								// checking if the member whom you want to ban is an admin
								if (whoToBan.hasPermission(Permission.ADMINISTRATOR)) {
									// if they are, don't ban them
									event.getChannel().sendMessage("You cannot ban other admins").queue();
								} else {
									// ban the member, the parameters are [who to ban] [delay] [reason]
									event.getGuild().ban(whoToBan, 0, banReason).queue(success -> {
										// if it successfully bans the user, send a success message
										event.getChannel()
												.sendMessage("Successfully banned " + whoToBan.getAsMention()
														+ "\nBanned by: " + modMention + "\nReason: " + banReason)
												.queue();
									});
								}
							});
						} else {
							event.getChannel().sendMessage("You do not have permission to ban users!").queue();
						}

					});

				}
			} else {
				event.getChannel().sendMessage("Invalid format, please use <ban [user] [reason *optional*]").queue();
			}

		}

		// <ban [user] [reason *optional*]
	}

}
