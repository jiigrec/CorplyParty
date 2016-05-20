package pro.jeanyves.party;

import net.md_5.bungee.api.plugin.Plugin;
import pro.jeanyves.party.command.PartyCommand;
import pro.jeanyves.party.config.Config;
import pro.jeanyves.party.listener.PlayerDisconnectListener;
import pro.jeanyves.party.listener.ServerSwitchListener;

public class Party extends Plugin {

	/**
	 * Instance of the plugin
	 */
	private static Party instance;

	/**
	 * Instance of the message file
	 */
	private Config messages;

	/**
	 * Instance of the MessageManager
	 */
	private static MessageManager messageManager;

	/**
	 * Method will be called when the plugin is enabling
	 */
	@Override
	public void onEnable() {
		instance = this;
		messages = new Config( "messages", this );
		messageManager = new MessageManager( messages );
		getProxy().getPluginManager().registerCommand( this, new PartyCommand() );
		getProxy().getPluginManager().registerListener( this, new PlayerDisconnectListener() );
		getProxy().getPluginManager().registerListener( this, new ServerSwitchListener() );
	}

	/**
	 * Method will be called when the plugin is disabling
	 */
	@Override
	public void onDisable() {
		//Rien
	}



	/**
	 * Getter for the instance of the plugin
	 * @return Instance of the plugin
	 */
	public static Party getInstance() {
		return instance;
	}

	/**
	 * Getter for the instance of the MessageManager
	 * @return Instance of the MessageManager
	 */
	public static MessageManager getMessageManager() {
		return messageManager;
	}
}
