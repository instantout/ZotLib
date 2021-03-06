package XZot1K.plugins.zl.libraries.inventorylib;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    /**
     * Remove a specific item from an inventory based on the itemstack's amount.
     *
     * @param inventory The inventory you want to remove the item from.
     * @param itemStack The itemstack you want to remove.
     */
    private void removeItem(Inventory inventory, ItemStack itemStack)
    {
        inventory.removeItem(itemStack);
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     * @param materialName The material name you want the item to be.
     * @param amount The amount you want the stack size to be.
     * @param durability The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(String materialName, int amount, short durability)
    {
        return new CustomItem(materialName, amount, durability);
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     * @param material The material you want the item to be.
     * @param amount The amount you want the stack size to be.
     * @param durability The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(Material material, int amount, short durability)
    {
        return new CustomItem(material, amount, durability);
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     * @param materialId The material id you want the item to be.
     * @param amount The amount you want the stack size to be.
     * @param durability The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(int materialId, int amount, short durability)
    {
        return new CustomItem(materialId, amount, durability);
    }

    /**
     * Creates a new CustomInventory, which is used to make a new Inventory (Custom GUI) with ease.
     * @param inventoryHolder The inventory holder you want to hold the inventory.
     * @param inventoryTitle The title of the inventory (Color Codes Enabled).
     * @param inventorySize The size you want the inventory to be (Must be divisible by 9 and less than or equal to 54).
     * @return A new CustomInventory object.
     */
    public CustomInventory createCustomInventory(InventoryHolder inventoryHolder, String inventoryTitle, int inventorySize)
    {
        return new CustomInventory(inventoryHolder, inventoryTitle, inventorySize);
    }

    /**
     * The "isEmpty" method allows you to check if the fed inventory is empty or not.
     *
     * @param inventory The inventory you want to check.
     * @return Whether the inventory is full or not.
     */
    public boolean isFull(Inventory inventory)
    {
        return inventory.firstEmpty() == -1;
    }

    /**
     * The "doItemsMatch" method allows you to check if two item stacks match EXACTLY (This also works with no item meta).
     * @param item1 The first item you want to compare.
     * @param item2 The second item you want to compare.
     * @return Whether the two items are the same (Checks ItemMeta if exists).
     */
    public boolean doItemsMatch(ItemStack item1, ItemStack item2)
    {
        if (item1.hasItemMeta() && item2.hasItemMeta())
        {
            if (item1.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName())
            {
                if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName()))
                {
                    if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore())
                    {
                        if (item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore()))
                        {
                            return true;
                        }
                    } else if (!item1.getItemMeta().hasLore() && !item2.getItemMeta().hasLore())
                    {
                        return true;
                    }
                }
            } else if (!item1.getItemMeta().hasDisplayName() && !item2.getItemMeta().hasDisplayName())
            {
                if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName()))
                {
                    if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore())
                    {
                        if (item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore()))
                        {
                            return true;
                        }
                    } else if (!item1.getItemMeta().hasLore() && !item2.getItemMeta().hasLore())
                    {
                        return true;
                    }
                }
            }
        } else
        {
            if (item1.getType() == item2.getType() && item1.getDurability() == item2.getDurability())
            {
                return true;
            }
        }
        return false;
    }

    /** The "doesInventoryTitleMatch" method allows you to check if a inventory's title matches the given text with or without color.
     *
     * @param inventory The inventory you want to check.
     * @param text The title text you want to compare to the inventory's title.
     * @param stripColor Whether you want to check with or without color.
     * @return Whether the inventory's title and the text match or not.
     */
    public boolean doesInventoryTitleMatch(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).equals(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().equals(plugin.getGeneralLibrary().color(text));
        }
    }

    /**
     * Checks if a inventory's title contains the given text with or without color.
     * @param inventory The inventory you want to check.
     * @param text The title text you want to see if the inventory's title contains it.
     * @param stripColor Whether you want to check with or without color.
     * @return Whether the inventory's title contains the text or not.
     */
    public boolean doesInventoryTitleContain(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).contains(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().contains(plugin.getGeneralLibrary().color(text));
        }
    }

    /**
     * The "doesInventoryTitleMatch" method allows you to check if a inventory's title ends with the given text with or without color.
     * @param inventory The inventory you want to check.
     * @param text The title text you want to see if the inventory's title ends with.
     * @param stripColor Whether you want to check with or without color.
     * @return Whether the inventory's title ends with the text or not.
     */
    public boolean doesInventoryTitleEndWith(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).endsWith(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().endsWith(plugin.getGeneralLibrary().color(text));
        }
    }

    /**
     * The "doesInventoryTitleMatch" method allows you to check if a inventory's title starts with the given text with or without color.
     * @param inventory The inventory you want to check.
     * @param text The title text you want to see if the inventory's title starts with.
     * @param stripColor Whether you want to check with or without color.
     * @return Whether the inventory's title starts with the text or not.
     */
    public boolean doesInventoryTitleStartWith(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).startsWith(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().startsWith(plugin.getGeneralLibrary().color(text));
        }
    }

}
