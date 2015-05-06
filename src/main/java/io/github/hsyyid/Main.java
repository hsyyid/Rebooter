package io.github.hsyyid;

import org.spongepowered.api.Server;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.ServerStartedEvent;
import org.spongepowered.api.event.state.ServerStoppingEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.command.CommandService;

import com.google.inject.Inject;

import org.slf4j.Logger;

@Plugin(id = "Rebooter", name = "Rebooter", version = "0.1")
public class Main {
	//Setting up Plugin Logger.
	@Inject
	private Logger logger;
	
	public Logger getLogger() {
	    return logger;
	}
	
	@Subscribe
    public void onServerStart(ServerStartedEvent event) {
		getLogger().info("Plugin has loaded! Don't worry about crashing now :D");
        
		Server server = event.getGame().getServer();
	      
        CommandService cmdService = event.getGame().getCommandDispatcher();
        cmdService.register(this, new Command(server), "rebooter");
	}
	
	@Subscribe
	public void onServerStopping(ServerStoppingEvent event){
		//Run BATCH FILE when Server Stops.
		try {
            String[] command = { "cmd.exe", "/C", "Start", "run.bat"};
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(command);
                p.waitFor();

                } catch (Exception e) {
                	getLogger().warn("ERROR: FILE NOT FOUND! Did you name it run.bat?");
                }
	}
}
