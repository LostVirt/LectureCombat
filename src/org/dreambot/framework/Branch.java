package org.dreambot.framework;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Branch<T extends AbstractScript> extends Leaf<T> {

    public final List<Leaf<T>> children;

    public Branch() {
        this.children = new LinkedList<>();
    }


    public Branch<T> addLeafs(Leaf<T>... leaves) {
        Collections.addAll(this.children, leaves);
        return this;
    }


    @Override
    public int onLoop(T context) {
        return children.stream()
                .filter(c -> Objects.nonNull(c) && c.isValid(context))
                .findAny()
                .map(tLeaf -> {

                    return tLeaf.onLoop(context);
                }).orElse((int) Calculations.nextGaussianRandom(350, 250));

    }
}
