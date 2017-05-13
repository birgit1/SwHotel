
package com.birgit.swhotel.technical;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


public class LoggerFactory 
{
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint)
        {
            Logger logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getSimpleName());
		return logger;

	}	

}

