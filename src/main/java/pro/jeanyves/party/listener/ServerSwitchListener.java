package pro.jeanyves.party.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pro.jeanyves.party.PartyManager;
import pro.jeanyves.party.PlayerParty;

public class ServerSwitchListener implements Listener {
	
	@EventHandler
	public void onServerSwitch(ServerSwitchEvent e) {
		ProxiedPlayer player = e.getPlayer();
		if(PartyManager.getParty(player) != null) {
			PlayerParty party = PartyManager.getParty(player);
			if(party.isLeader(player)) {
				for(ProxiedPlayer p : party.getPlayers()) {
					p.connect(party.getServer());
				}
			}
		}
	}

}
