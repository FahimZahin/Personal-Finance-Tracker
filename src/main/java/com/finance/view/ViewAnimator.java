package com.finance.view;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class ViewAnimator {

    /**
     * Applies a smooth fade-in transition when switching content views.
     */
    public static void fadeIn(Node node) {
        if (node == null) return;

        node.setOpacity(0.0);
        FadeTransition fade = new FadeTransition(Duration.millis(350), node);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
    }
}