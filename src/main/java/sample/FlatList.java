package sample;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by w00lf on 28.06.2015.
 */
public class FlatList {
    Set<Flat> entries = new HashSet<Flat>();

    public final void add(final Flat flat) {
        entries.add(flat);
    }

    public final void remove(final Flat flat) {
        entries.remove(flat);
    }
}
