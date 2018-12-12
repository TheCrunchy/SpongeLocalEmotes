package LocalEmotes;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.Collection;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.Entity;

@Plugin(id = "localemotes", name = "Local Emotes", version = "1.0", description = "Local emotes for RP")
public class main {
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        Object plugin = this;
		// Hey! The server has started!
        // Try instantiating your logger in here.
        // (There's a guide for that)
    }
    @Listener
    public void onServerFinishLoad(GameInitializationEvent event) {
        Object plugin = this;
		// Hey! The server has started!
        // Try instantiating your logger in here.
        // (There's a guide for that)
       	Sponge.getCommandManager().register(plugin, myCommandSpec, "me", "em", "emote");
    }
    Player player;
    String playernick;
    CommandSpec myCommandSpec = CommandSpec.builder()
    	    .description(Text.of("Local emotes command"))
    	    .permission("localemotes.use")
    	    .executor(new localEmote())
            .arguments(
                    GenericArguments.remainingJoinedStrings(Text.of("message")))
    	    .build();

    	public class localEmote implements CommandExecutor {

    	    @Override
    	    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
    	    	player = (Player) src;
    	    	playernick = src.getName();
                Collection<Entity> entities = player.getNearbyEntities(20);
    	    	String message = args.<String>getOne("message").get();
                for(Entity entity : entities) {
                    if(entity instanceof Player) {
                    	//player = ((Player) entity).getPlayer();
                        ((MessageReceiver) entity).sendMessage(Text.of(TextColors.WHITE , "[*] " , TextColors.GREEN , playernick , " " ,  TextColors.GREEN , message));
                    }
                }
    	       // player.sendMessage(Text.of(TextColors.WHITE , "[*] " , TextColors.GREEN , playernick , " " ,  TextColors.GREEN , message));
    	        return CommandResult.success();
    	    }
    	}   	
}
