package util;

import model.annotation.Positive;
import model.annotation.MaxLength;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationValidator {

    public static String[] validate(Object obj) {
        ArrayList<String> errors = new ArrayList<>();

        Class<?> currentClass = obj.getClass();

        // Check the class and its parent classes
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                try {
                    Object value = field.get(obj);

                    // Check @Positive
                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive positive =
                                field.getAnnotation(Positive.class);

                        if (value instanceof Number
                                && ((Number) value).doubleValue() <= 0) {
                            errors.add(field.getName() + " "
                                    + positive.message());
                        }
                    }

                    // Check @MaxLength
                    if (field.isAnnotationPresent(MaxLength.class)) {
                        MaxLength maxLength =
                                field.getAnnotation(MaxLength.class);

                        if (value instanceof String
                                && ((String) value).length()
                                > maxLength.value()) {
                            errors.add(field.getName()
                                    + " must have at most "
                                    + maxLength.value()
                                    + " characters");
                        }
                    }

                } catch (IllegalAccessException e) {
                    errors.add("Cannot access field: "
                            + field.getName());
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
