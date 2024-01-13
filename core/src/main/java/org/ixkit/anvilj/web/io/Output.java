package org.ixkit.anvilj.web.io;

import org.ixkit.land.lang.Argument;

import java.util.Random;

public class Output {

    public static Argument toErrorResponse(Exception ex){
        String key = ex.getClass().getSimpleName();
        Argument error = Argument.of(key ,ex.getMessage());
        return error;
    }
}
