package io.github.hsyyid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spongepowered.api.Game;
import org.spongepowered.api.Server;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

import com.google.common.base.Optional;
import com.google.inject.Inject;

public class Command implements CommandCallable {
    private final Server server;
    //Remember, to use TEXT you MUST cast it.
    private final Optional<Text> desc = Optional.of((Text) Texts.of("Rebooter enables servers to automatically restart upon crashing."));
    private final Optional<Text> help = Optional.of((Text) Texts.of("To use Rebooter use /rebooter."));
    List<String> suggestions = new ArrayList<String>();

    public Command(Server server) {
        this.server = server;
    }

	public Optional<Text> getHelp(CommandSource arg0) {
		return help;
	}

	public Optional<Text> getShortDescription(CommandSource arg0) {
		return desc;
	}

	public List<String> getSuggestions(CommandSource arg0, String arg1)
			throws CommandException {
		return suggestions;
	}

	public Text getUsage(CommandSource arg0) {
		return ((Text) Texts.of("Use /rebooter reload to reload the config."));
	}

	public Optional<CommandResult> process(CommandSource source, String arguments)
			throws CommandException {
		if(server.getOnlinePlayers() != null){
			for (Player player : server.getOnlinePlayers()) {
            	player.sendMessage(((Text) Texts.of("Rebooter makes sure your server automatically restarts upon crashing or stopping. To make sure Rebooter works, please make sure your start script is run.bat")));
            	//("You love cute " + arguments);
        	}
		}
		//This is where the command actually does something..
		return Optional.of(CommandResult.success());
	}
	public boolean testPermission(CommandSource source) {
		return source.hasPermission("rebooter.use");
	}
}