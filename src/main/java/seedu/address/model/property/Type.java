package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.property.PropertyType.isValidEnumValue;

/**
 * Represents a property type in the address book.
 * Guarantees: immutable; type is valid as declared in {@link #isValidType(String)}.
 */
public class Type {
    /**
     * Specifies allowed property types
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Type can only be case-insensetive HDB, CONDO, LANDED";
    public static final String VALIDATION_REGEX = String.format("^(?i)(%s)$", String.join("|",
            java.util.Arrays.stream(PropertyType.values())
                    .map(Enum::name)
                    .toArray(String[]::new)
    ));
    public final String value;

    /**
     * Constructs a {@code Type}.
     *
     * @param type A valid type.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = PropertyType.valueOf(type.toUpperCase()).toString();
    }

    /**
     * Returns true if a given string is a valid type and contained in the enum.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX) && isValidEnumValue(test);
    }

    /**
     * Returns true if a given {@code Type} is a Landed property.
     */
    public boolean isLandedType() {
        return value.equals(PropertyType.LANDED.toString());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Type)) {
            return false;
        }

        Type otherType = (Type) other;
        return value.equals(otherType.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}