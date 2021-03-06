package XZot1K.plugins.zl;

import XZot1K.plugins.zl.libraries.CalculationLibrary;
import XZot1K.plugins.zl.libraries.GeneralLibrary;
import XZot1K.plugins.zl.libraries.PacketLibrary;
import XZot1K.plugins.zl.libraries.inventorylib.InventoryLibrary;
import metrics.Metrics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ZotLib extends JavaPlugin
{

    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    private InventoryLibrary inventoryLibrary;
    private GeneralLibrary generalLibrary;
    private PacketLibrary packetLibrary;
    private CalculationLibrary calculationLibrary;

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        new Manager(this);
        inventoryLibrary = new InventoryLibrary();
        generalLibrary = new GeneralLibrary();
        packetLibrary = new PacketLibrary();
        calculationLibrary = new CalculationLibrary();
        if (getConfig().getBoolean("setup-packets"))
        {
            getPacketLibrary().setupPackets();
        }
        try
        {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMetrics failed to start. Please check your connection.");
        }
        getCommand("zotlib").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (command.getName().equalsIgnoreCase("zotlib"))
        {
            if (commandSender.hasPermission("zotlib.reload"))
            {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("reload"))
                    {
                        reloadConfig();
                        getPacketLibrary().setupPackets();
                        commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("reload-message")));
                        return true;
                    }
                }

                commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("usage-message")));
                return true;
            } else
            {
                commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("no-permission-message")));
                return true;
            }
        }
        return false;
    }

    public InventoryLibrary getInventoryLibrary()
    {
        return inventoryLibrary;
    }

    public GeneralLibrary getGeneralLibrary()
    {
        return generalLibrary;
    }

    public PacketLibrary getPacketLibrary()
    {
        return packetLibrary;
    }

    public String getServerVersion()
    {
        return serverVersion;
    }

    public String getPrefix()
    {
        return "&bZotLib &7-> ";
    }

    public CalculationLibrary getCalculationLibrary()
    {
        return calculationLibrary;
    }

}
