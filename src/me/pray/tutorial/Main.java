package me.pray.tutorial;

import javax.security.auth.login.LoginException;

import me.pray.tutorial.commands.HelloCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

	public static String PREFIX = "!";
	
	public static void main(String[] args) throws LoginException {
		
		String token = "ODU0MDQ4NzgyODUzNDcyMzE3.YMeQ6Q.7IJyW_o0LiJ5zpCmZMEoYYWMa-k";
		
		JDABuilder jda = JDABuilder.createDefault(token);
		jda.setStatus(OnlineStatus.IDLE);
		jda.setActivity(Activity.watching(PREFIX + "help"));
		jda.addEventListeners(new HelloCommand());
		
		jda.build();
		
	}
	
}
