package com.casa.agent_service.agent;

import com.casa.agent_service.entity.Post;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * This class provides custom implementations for the 'save' method and an advice for method entry.
 * It is used by the Agent class to redefine the 'save' method of the SimpleJpaRepository class in both 'RECORD' and
 * 'REPLAY' modes.
 */
public class CustomJpaRepository {
    private static final String response = "ok look nice";

    /**
     * This method is used to provide a static response when the 'save' method of the SimpleJpaRepository class is
     * called in 'replay' mode.
     * It creates a new Post object with dummy data and returns it.
     *
     * @param <S> The type of the entity.
     * @param <T> The type of the entity.
     * @param entity The entity to be saved.
     * @return A Post object with dummy data.
     */
    public static <S extends T, T> S save(S entity) {
        try{
            Class<?> entityClass = entity.getClass();
            S newEntity = (S) entityClass.getDeclaredConstructor().newInstance();
            Field[] fields = entityClass.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                Type type = field.getGenericType();
                if (type.equals(Long.class))
                    field.set(newEntity, 1L);
                else if (type.equals(String.class))
                    field.set(newEntity, "dummy data");
            }
            return newEntity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * This advice is executed when a method is entered.
     * It prints the input entity to the console.
     *
     * @param entity The input entity.
     */
    @Advice.OnMethodEnter
    public static void enter(@Advice.Argument(0) Object entity) {
        System.out.println("Entity is: " + entity);
    }

}
