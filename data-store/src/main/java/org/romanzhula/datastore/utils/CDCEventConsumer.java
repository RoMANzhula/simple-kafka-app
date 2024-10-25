package org.romanzhula.datastore.utils;

public interface CDCEventConsumer { // Change Data Capture (CDC) - tracking change in the DB

    void handle(String message);
}
