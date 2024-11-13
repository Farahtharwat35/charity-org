package com.charity_org.demo.Patcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static void userPatcher(Object existingobject, Object incompleteObject) throws IllegalAccessException {

        //GETTING THE COMPILED VERSION OF THE CLASS
        Class<?> ObjectClass= existingobject.getClass();
        Field[] objectFields= ObjectClass.getDeclaredFields();
        System.out.println(objectFields.length);
        for(Field field : objectFields){
            System.out.println(field.getName());
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            //CHECKING IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING Object
            Object value=field.get(incompleteObject);
            if(value!=null){
                field.set(existingobject,value);
            }
            //MAKING THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }

    }
}
