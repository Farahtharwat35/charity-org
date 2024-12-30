package com.charity_org.demo.Classes.IteratorComponents;
import com.charity_org.demo.Models.Model.Event;
public interface EventIterator {
    boolean hasNext();
   Event next();
   Event reset();
}
