package me.pray.tutorial;

import javax.security.auth.login.LoginException;

import me.pray.tutorial.commands.BanCommand;
import me.pray.tutorial.commands.HelloCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

	public String prefix = "!";

	private static Main main = new Main();

	public static void main(String[] args) throws LoginException, InterruptedException {

		String token = "ODI5MDkzNDA4MTM5OTAyOTc3.YGzHaA.H92TcQmJ5lQCQpbRS8hIgKRHxDY";

		JDABuilder jda = JDABuilder.createDefault(token);
		jda.setStatus(OnlineStatus.ONLINE);
		jda.setActivity(Activity.watching("hello"));
		jda.addEventListeners(new HelloCommand(main), new BanCommand(main));

		jda.build();

	}

}
