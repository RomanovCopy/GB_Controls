package org.romanov.utilities;

import java.lang.AutoCloseable;

public class Counter implements AutoCloseable{
    private int count = 0;
    private boolean used = false;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws ResourceCloseException {
        if (!used) {
            throw new ResourceCloseException("Ресурс не использовался в блоке try-with-resources.");
        }
    }

    public void markAsUsed() {
        used = true;
    }
}
