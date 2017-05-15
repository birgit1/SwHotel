
package com.birgit.swhotel.utils;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


@Dependent
public class LoggerProvider 
{
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint)
        {
            Logger logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getSimpleName());
		return logger;

	}	

}

