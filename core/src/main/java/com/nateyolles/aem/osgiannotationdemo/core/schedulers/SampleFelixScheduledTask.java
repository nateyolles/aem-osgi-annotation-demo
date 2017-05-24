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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    metatype = true,
    label = "Annotation Demo Scheduler - Felix",
    description = "Sample Scheduler using Felix SCR annotations."
)
@Service(value = Runnable.class)
@Properties({
    @Property(
        name = "scheduler.expression",
        value = "0 * * * * ?",
        description = "Cron-job expression. Default: run every minute."
    ),
    @Property(
        name = "scheduler.concurrent",
        boolValue = false,
        description = "Schedule task concurrently"
    )
})
public class SampleFelixScheduledTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        logger.info("Sample Felix Scheduler is now running, myParameter='{}'", myParameter);
    }

    @Property(
        label = "My Parameter",
        description = "Sample String parameter"
    )
    public static final String MY_PARAMETER = "myParameter";
    private String myParameter;

    @Activate
    protected void activate(final Map<String, Object> config) {
        configure(config);
    }

    private void configure(final Map<String, Object> config) {
        myParameter = PropertiesUtil.toString(config.get(MY_PARAMETER), StringUtils.EMPTY);
    }
}
