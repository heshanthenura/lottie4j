package com.lottie4j.core.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lottie4j.core.exception.LottieModelDefinitionException;
import com.lottie4j.core.info.DefinitionWithLabel;

import java.util.Arrays;

/**
 * https://lottiefiles.github.io/lottie-docs/constants/#blendmode
 */
public enum MatteMode implements DefinitionWithLabel {
    NORMAL(0, "Normal"),
    ALPHA(1, "Alpha"),
    INVERTED_ALPHA(2, "Inverted Alpha"),
    LUMA(3, "Luma"),
    INVERTED_LUMA(4, "Inverted Luma");

    @JsonValue
    private final int value;
    private final String label;

    MatteMode(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Some files seem to contain decimal values. So some extra convertion is needed.
     */
    @JsonCreator
    public static MatteMode fromValue(String value) throws LottieModelDefinitionException {
        return Arrays.stream(MatteMode.values()).sequential()
                .filter(v -> Math.round(Double.valueOf(value)) == v.value)
                .findFirst()
                .orElseThrow(() -> new LottieModelDefinitionException(MatteMode.class, value));
    }

    public int value() {
        return value;
    }

    @Override
    public String label() {
        return label;
    }
}
