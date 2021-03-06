package pro.jeanyves.party.command;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import pro.jeanyves.party.Party;
import pro.jeanyves.party.PartyManager;
import pro.jeanyves.party.PlayerParty;

import java.util.Arrays;

public class Join extends SubCommand {
	
	public Join() {
		super(Party.getMessageManager().getRawString("Commands.Join.Help"), Party.getMessageManager().getRawString("Commands.Join.Usage"), "join");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(Party.getMessageManager().getString("Commands.Join.NoPlayer"));
			return;
		}
		
		if(PartyManager.getParty(p) != null) {
			p.sendMessage(Party.getMessageManager().getString("Commands.Join.AlreadyInParty"));
			return;
		}
		
		ProxiedPlayer pl = Party.getInstance().getProxy().getPlayer(args[0]);
		
		if(pl == null) {
			p.sendMessage(Party.getMessageManager().getString("Commands.Join.NotOnline"));
			return;
		}
		
		if(PartyManager.getParty(pl) == null) {
			p.sendMessage(Party.getMessageManager().getString("Commands.Join.NoParty"));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(pl);
		
		if(party.addPlayer(p)) {
			for(ProxiedPlayer pp : party.getPlayers()) {
				pp.sendMessage(Party.getMessageManager().getString("Commands.Join.Joined", Arrays.asList("%player%"), Arrays.asList(p.getName())));
			}
			party.getLeader().sendMessage(Party.getMessageManager().getString("Commands.Join.Joined", Arrays.asList("%player%"), Arrays.asList(p.getName())));
		}else {
			p.sendMessage(Party.getMessageManager().getString("Commands.Join.Error"));
		}
 	}

}
