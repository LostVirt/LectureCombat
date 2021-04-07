package org.dreambot.behaviour.fighting;

import org.dreambot.Main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.util.API;

public class Fight extends Leaf<Main> {

    @Override
    public boolean isValid(Main context) {
        return !Players.localPlayer().isInCombat();
    }

    @Override
    public int onLoop(Main context) {

        NPC chicken = NPCs.closest(npc -> npc != null && npc.getName().equals(API.CHICKEN) && !npc.isInCombat());
        if (chicken != null && chicken.interact("Attack")) {
            MethodProvider.sleepUntil(() -> Players.localPlayer().isInCombat(), 2500);
        }

        return API.sleep;
    }
}
