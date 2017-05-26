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

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Annotation Demo Service - OSGi")
public @interface Configuration {

    @AttributeDefinition(
        name = "Boolean Property",
        description = "Sample boolean value",
        type = AttributeType.BOOLEAN
    )
    boolean servicename_propertyname_boolean() default true;

    @AttributeDefinition(
        name = "String Property",
        description = "Sample String property",
        type = AttributeType.STRING
    )
    String servicename_propertyname_string() default "foo";

    @AttributeDefinition(
        name = "Dropdown property",
        description = "Sample dropdown property",
        options = {
            @Option(label = "DAYS", value = "DAYS"),
            @Option(label = "HOURS", value = "HOURS"),
            @Option(label = "MILLISECONDS", value = "MILLISECONDS"),
            @Option(label = "MINUTES", value = "MINUTES"),
            @Option(label = "SECONDS", value = "SECONDS")
        }
    )
    String servicename_propertyname_dropdown() default StringUtils.EMPTY;

    @AttributeDefinition(
        name = "String Array Property",
        description = "Sample String array property",
        type = AttributeType.STRING
    )
    String[] servicename_propertyname_string_array() default {"foo", "bar"};

    /*
     * To create password field, either set the AttributeType or have the
     * property name end with "*.password" (or both).
     */
    @AttributeDefinition(
        name = "Password Property",
        description = "Sample password property",
        type = AttributeType.PASSWORD
    )
    String servicename_propertyname_password() default StringUtils.EMPTY;

    @AttributeDefinition(
        name = "Long Property",
        description = "Sample long property",
        type = AttributeType.LONG
    )
    long servicename_propertyname_long() default 0L;
}
