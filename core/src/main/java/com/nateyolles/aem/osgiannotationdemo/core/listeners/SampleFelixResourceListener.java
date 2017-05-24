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

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    immediate = true
)
@Service(value = EventHandler.class)
@Properties({
    /*
     * Set propertyPrivate = true if you generate metatype = true.
     */
    @Property(
        name = EventConstants.EVENT_TOPIC,
        value = {
            SlingConstants.TOPIC_RESOURCE_ADDED,
            SlingConstants.TOPIC_RESOURCE_CHANGED,
            SlingConstants.TOPIC_RESOURCE_REMOVED
        }
    ),
    @Property(
        name = EventConstants.EVENT_FILTER,
        //propertyPrivate = true,
        value = "(path=/content/*)"
    )
})
public class SampleFelixResourceListener implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void handleEvent(final Event event) {
        logger.info("Felix EventHander: {} at: {}",
            event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
    }
}
