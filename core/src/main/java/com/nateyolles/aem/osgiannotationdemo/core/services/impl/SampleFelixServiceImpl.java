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

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyOption;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.osgi.PropertiesUtil;

import com.nateyolles.aem.osgiannotationdemo.core.services.SampleFelixService;

@Component(
    metatype = true,
    label = "Annotation Demo Service - Felix",
    description = "Sample service with various Felix SCR annotations"
)
@Service(value = SampleFelixService.class)
@Properties({
    @Property(
        label = "Boolean Property",
        name = SampleFelixServiceImpl.BOOLEAN_PROPERTY_NAME,
        description = "Sample boolean value",
        boolValue = SampleFelixServiceImpl.BOOLEAN_PROPERTY_DEFAULT_VALUE
    ),
    @Property(
        label = "String Property",
        description = "Sample String property",
        name = SampleFelixServiceImpl.STRING_PROPERTY_NAME,
        value = SampleFelixServiceImpl.STRING_PROPERTY_DEFAULT_VALUE
    ),
    @Property(
        label = "Dropdown Property",
        name = SampleFelixServiceImpl.DROPDOWN_PROPERTY_NAME,
        description = "Sample dropdown property",
        value=SampleFelixServiceImpl.DROPDOWN_PROPERTY_DEFAULT_VALUE,
        options = {
            @PropertyOption(name = "DAYS", value = "DAYS"),
            @PropertyOption(name = "HOURS", value = "HOURS"),
            @PropertyOption(name = "MILLISECONDS", value = "MILLISECONDS"),
            @PropertyOption(name = "MINUTES", value = "MINUTES"),
            @PropertyOption(name = "SECONDS", value = "SECONDS")
    }),
    /*
     * To create a user expandable field of Strings, use either
     * unbounded = PropetyUnbounded.ARRAY or cardinality = a positive integer
     * such as Integer.MAX_VALUE.
     */
    @Property(
        label = "String Array Property (unbounded)",
        name = SampleFelixServiceImpl.STRING_ARRAY_PROPERTY_NAME,
        description = "Sample String array property (unbounded)",
        unbounded = PropertyUnbounded.ARRAY
    ),
    /*
     * To create password field, either set the AttributeType or have the
     * property name end with "*.password" (or both).
     */
    @Property(
        label = "Password Property",
        description = "Sample password property",
        name = SampleFelixServiceImpl.PASSWORD_PROPERTY_NAME,
        passwordValue = StringUtils.EMPTY
    ),
    @Property(
        label = "Long Property",
        description = "Sample long property",
        name = SampleFelixServiceImpl.LONG_PROPERTY_NAME,
        longValue = SampleFelixServiceImpl.LONG_PROPERTY_DEFAULT_VALUE
    )
})
public class SampleFelixServiceImpl implements SampleFelixService {

    @Reference
    private ResourceResolverFactory resolverFactory;


    public static final String BOOLEAN_PROPERTY_NAME = "servicename.propertyname.boolean";
    public static final boolean BOOLEAN_PROPERTY_DEFAULT_VALUE = false;

    public static final String STRING_PROPERTY_NAME = "servicename.propertyname.string";
    public static final String STRING_PROPERTY_DEFAULT_VALUE = "foo";

    public static final String PASSWORD_PROPERTY_NAME = "servicename.propertyname.password";

    public static final String STRING_ARRAY_PROPERTY_NAME = "servicename.propertyname.stringarray.unbounded";

    public static final String STRING_ARRAY_2_PROPERTY_NAME = "servicename.propertyname.stringarray.cardinality";

    public static final String DROPDOWN_PROPERTY_NAME = "servicename.propertyname.dropdown";
    public static final String DROPDOWN_PROPERTY_DEFAULT_VALUE = "SECONDS";

    public static final String LONG_PROPERTY_NAME = "servicename.propertyname.long";
    public static final long LONG_PROPERTY_DEFAULT_VALUE = 0L;

    boolean booleanProp;
    String stringProp;
    String dropdownProp;
    String[] stringArrayProp;
    char[] passwordProp;
    long longProp;

    @Override
    public String getSettings() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sample Felix Service:\n");
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
    protected final void activate(final Map<String, Object> config) {
        Map<String, Object> properties = Collections.emptyMap();

        if (config != null) {
            properties = config;
        }

        booleanProp = PropertiesUtil.toBoolean(properties.get(BOOLEAN_PROPERTY_NAME), BOOLEAN_PROPERTY_DEFAULT_VALUE);
        stringProp = PropertiesUtil.toString(properties.get(STRING_PROPERTY_NAME), STRING_PROPERTY_DEFAULT_VALUE);
        dropdownProp = PropertiesUtil.toString(properties.get(DROPDOWN_PROPERTY_NAME), DROPDOWN_PROPERTY_DEFAULT_VALUE);
        stringArrayProp = PropertiesUtil.toStringArray(properties.get(STRING_ARRAY_PROPERTY_NAME));
        passwordProp = PropertiesUtil.toString(properties.get(PASSWORD_PROPERTY_NAME), "").toCharArray();
        longProp = PropertiesUtil.toLong(properties.get(LONG_PROPERTY_NAME), LONG_PROPERTY_DEFAULT_VALUE);
    }

    @Deactivate
    protected void deactivate() {
    }
}
