package org.ixkit.anvilj.launcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @class:RunArgument
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 15/07/2022
 * @version:0.1.0
 * @purpose:
 */
@Slf4j
@Component
public class RunArgument {
    private ApplicationArguments arguments;
    @Autowired
    public RunArgument(ApplicationArguments args) {
        this.arguments = args;

        List<String> _args = args.getNonOptionArgs();
        log.debug("Extra command ...");

        if (!_args.isEmpty()) _args.forEach(x ->{
            log.debug(x);
        });

        log.debug("Extra all ...");
        String[] sourceArgs = args.getSourceArgs();

        for (String sourceArg : sourceArgs) {
            log.debug(sourceArg);
        }
    }

    public String getOption(String name){
        if ( !arguments.containsOption(name)){
            return null;
        }
        List<String> optionValues  =  arguments.getOptionValues(name);
        return optionValues.get(0);
    }

    public boolean getCommand(String name){
        List<String> optionValues = arguments.getNonOptionArgs();
        return  optionValues.stream().anyMatch(x->{
            return x.equals(name);
        });
    }
}
