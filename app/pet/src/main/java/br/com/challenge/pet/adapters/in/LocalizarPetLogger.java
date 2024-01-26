package br.com.challenge.pet.adapters.in;

import lombok.extern.java.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalizarPetLogger {

    private static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void info(String message, Object body) {
        logger.log(Level.INFO, message + " - " + body.toString());
    }

    public static void warn(String message, Object body) {
        logger.log(Level.WARNING, message + " - " + body.toString());
    }

    public static void error(String message, Object body) {
        logger.log(Level.SEVERE, message + " - " + body.toString());
    }

}
