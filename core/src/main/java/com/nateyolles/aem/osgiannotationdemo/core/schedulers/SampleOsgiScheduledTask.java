/*
 *  Copyright 2017 Nate Yolles
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nateyolles.aem.osgiannotationdemo.core.schedulers;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    immediate = true,
    configurationPid = "com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleOsgiScheduledTask"
    // If you wanted the properties to be private
    // property = {
    //     "scheduler.expression=* * * * * ?",
    //     "scheduler.concurrent:Boolean=false"
    // }
)
@Designate(ocd=SampleOsgiScheduledTask.Configuration.class)
public class SampleOsgiScheduledTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        logger.info("Sample OSGi Scheduler is now running, myParameter='{}'", myParameter);
    }

    private String myParameter;
    
    @Activate
    protected void activate(Configuration config) {
        myParameter = config.myParameter();
    }

    @ObjectClassDefinition(name="Annotation Demo Scheduler - OSGi")
    public @interface Configuration {
        @AttributeDefinition(
            name="My parameter",
            description="Sample String parameter")
        String myParameter() default StringUtils.EMPTY;

        @AttributeDefinition(
            name = "Concurrent",
            description = "Schedule task concurrently",
            type = AttributeType.BOOLEAN
        )
        boolean scheduler_concurrent() default true;

        @AttributeDefinition(
            name = "Expression",
            description = "Cron-job expression. Default: run every minute.",
            type = AttributeType.STRING
        )
        String scheduler_expression() default "0 * * * * ?";
    }
}
