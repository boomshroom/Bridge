package org.spout.bridge.workbench.server;

import org.spout.api.util.access.BanType;
import org.spout.bridge.VanillaBridgePlugin;
import org.spout.bridge.bukkit.scheduler.BridgeScheduler;
import org.spout.bridge.workbench.server.players.WorkbenchBanList;

import net.minecraft.workbench.server.Server;
import net.minecraft.workbench.server.players.BanEntry;
import net.minecraft.workbench.server.players.BanList;

public class WorkbenchServer implements Server {

	private final org.spout.api.Server server;
	private final VanillaBridgePlugin plugin;
	private final BridgeScheduler scheduler = new BridgeScheduler();
	
	public WorkbenchServer(org.spout.api.Server server, VanillaBridgePlugin plugin){
		this.server=server;
		this.plugin=plugin;
	}
	
	@Override
	public BanList getBanList() {
		return new WorkbenchBanList();
	}

	@Override
	public void setBanList(BanList banlist) {
		for(BanEntry entry:banlist.getEntries().values()){
			if(!server.getAccessManager().isBanned(BanType.PLAYER, entry.getName())){
				server.getAccessManager().ban(BanType.PLAYER, entry.getName());
			}
		}
	}

}
