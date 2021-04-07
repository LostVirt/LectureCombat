package org.dreambot;

import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.behaviour.FighterBranch;
import org.dreambot.behaviour.fighting.Fight;
import org.dreambot.framework.Branch;
import org.dreambot.framework.Tree;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;

import java.awt.*;

@ScriptManifest(category = Category.COMBAT, name = "casBro", author = "Potato", version = 1)
public class Main extends AbstractScript implements PaintInfo {

    private final Tree<Main> tree = new Tree<>();

    private Branch<Main> fighterBranch;



    @Override
    public void onStart() {
        SkillTracker.start(Skill.STRENGTH);
        fighterBranch = new FighterBranch();

        tree.addBranches(
                fighterBranch.addLeafs(new Fight())

        );
    }

    @Override
    public int onLoop() {

        return this.tree.onLoop(this);
    }

    @Override
    public String[] getPaintInfo() {
        return new String[] {
                getManifest().name() + " V" + getManifest().version(),
                "Runtime: " + CUSTOM_PAINT.getRuntimeString(),
                "Strength Gained: " + SkillTracker.getGainedExperience(Skill.STRENGTH)
        };
    }

    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.TOP_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);

    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    @Override
    public void onPaint(Graphics2D graphics) {
        graphics.setRenderingHints(aa);

        CUSTOM_PAINT.paint(graphics);
    }
}
