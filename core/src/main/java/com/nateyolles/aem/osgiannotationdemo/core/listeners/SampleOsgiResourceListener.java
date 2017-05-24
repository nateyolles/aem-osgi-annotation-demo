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
package com.nateyolles.aem.osgiannotationdemo.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    immediate = true,
    service = EventHandler.class,
    property = {
        EventConstants.EVENT_FILTER + "=(path=/content/*)",
        EventConstants.EVENT_TOPIC + "=" + SlingConstants.TOPIC_RESOURCE_ADDED,
        EventConstants.EVENT_TOPIC + "=" + SlingConstants.TOPIC_RESOURCE_CHANGED,
        EventConstants.EVENT_TOPIC + "=" + SlingConstants.TOPIC_RESOURCE_REMOVED
    },
    configurationPid = "com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleOsgiScheduledTask"
)
public class SampleOsgiResourceListener implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void handleEvent(final Event event) {
        logger.info("OSGi EventHandler: {} at: {}",
                event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
