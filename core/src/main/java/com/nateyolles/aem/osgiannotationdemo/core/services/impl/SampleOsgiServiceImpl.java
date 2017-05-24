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
package com.nateyolles.aem.osgiannotationdemo.core.services.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import com.nateyolles.aem.osgiannotationdemo.core.services.SampleOsgiService;

@Component(
    immediate = true,
    service = SampleOsgiService.class,
    configurationPid = "com.nateyolles.aem.osgiannotationdemo.core.services.impl.SampleOsgiServiceImpl"
)
@Designate(
    ocd = Configuration.class
)
public class SampleOsgiServiceImpl implements SampleOsgiService {

    @Reference
    private ResourceResolverFactory resolverFactory;

    boolean booleanProp;
    String stringProp;
    String dropdownProp;
    String[] stringArrayProp;
    char[] passwordProp;
    long longProp;

    @Override
    public String getSettings() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sample OSGi Service:\n");
        sb.append("booleanProp: " + booleanProp + "\n");
        sb.append("stringProp: " + stringProp + "\n");
        sb.append("dropdownProp: " + dropdownProp + "\n");
        sb.append("stringArrayProp: " + ArrayUtils.toString(stringArrayProp) + "\n");
        sb.append("passwordProp: " + String.valueOf(passwordProp) + "\n");
        sb.append("longProp: " + longProp + "\n");

        return sb.toString();
    }

    @Activate
    @Modified
    protected final void activate(Configuration config) {
        booleanProp = config.servicename_propertyname_boolean();
        stringProp = config.servicename_propertyname_string();
        dropdownProp = config.servicename_propertyname_dropdown();
        stringArrayProp = config.servicename_propertyname_string_array();
        passwordProp = config.servicename_propertyname_password().toCharArray();
        longProp = config.servicename_propertyname_long();
    }

    @Deactivate
    protected void deactivate() {
    }
}
