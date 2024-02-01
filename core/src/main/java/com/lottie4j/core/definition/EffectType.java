package com.lottie4j.core.definition;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lottie4j.core.exception.LottieModelDefinitionException;
import com.lottie4j.core.info.DefinitionWithLabel;

import java.util.Arrays;

/**
 * https://lottiefiles.github.io/lottie-docs/effects/
 */
public enum EffectType implements DefinitionWithLabel {
    NORMAL(5, "Old-style Effect"),
    PAINT_OVER_TRANSPARENT(7, "Paint Over Transparent"),
    TINT(20, "Tint"),
    FILL(21, "Fill"),
    STROKE(22, "Stroke"),
    TRITONE(23, "Tritone"),
    PRO_LEVELS(24, "Pro Levels"),
    DROP_SHADOW(25, "Drop Shadow"),
    RADIAL_WIPE(26, "Radial Wipe"),
    DISPLACEMENT_MAP(27, "Displacement Map"),
    MATTE3(28, "Matte3"),
    GAUSSIAN_BLUR(29, "Gaussian Blur"),
    MESH_WARP(31, "Mesh Warp"),
    WAVY(32, "Wavy"),
    SPHERIZE(33, "Spherize"),
    PUPPET(34, "Puppet");

    @JsonValue
    private final int value;
    private final String label;

    EffectType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * Some files seem to contain decimal values. So some extra convertion is needed.
     */
    @JsonCreator
    public static EffectType fromValue(String value) throws LottieModelDefinitionException {
        return Arrays.stream(EffectType.values()).sequential()
                .filter(v -> Math.round(Double.valueOf(value)) == v.value)
                .findFirst()
                .orElseThrow(() -> new LottieModelDefinitionException(EffectType.class, value));
    }

    public int value() {
        return value;
    }

    @Override
    public String label() {
        return label;
    }
}
