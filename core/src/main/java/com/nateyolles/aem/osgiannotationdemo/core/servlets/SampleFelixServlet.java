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
package com.nateyolles.aem.osgiannotationdemo.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.osgi.PropertiesUtil;

import com.nateyolles.aem.osgiannotationdemo.core.services.SampleFelixService;

@SlingServlet(
    metatype = true,
    paths = {"/bin/felix"},
    methods = "GET",
    label = "Annotation Demo Servlet - Felix",
    description = "Sample servlet using Felix SCR annotations"
)
public class SampleFelixServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private SampleFelixService sampleFelixService;

    @Property(
        label = "Enable",
        name = SampleFelixServlet.ENABLE_SERVICE,
        description = "Sample boolean property",
        boolValue = SampleFelixServlet.ENABLE_SERVICE_DEFAULT
    )
    public static final String ENABLE_SERVICE = "enabled";
    private static final boolean ENABLE_SERVICE_DEFAULT = false;
    private boolean enabled;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        resp.setContentType("text/plain");
        out.write("Annotation Demo Servlet - Felix - enabled: " + enabled + "\n");
        out.write(sampleFelixService.getSettings());
    }

    @Activate
    @Modified
    protected final void activate(final Map<String, Object> config) {
        Map<String, Object> properties = Collections.emptyMap();

        if (config != null) {
            properties = config;
        }

        enabled = PropertiesUtil.toBoolean(properties.get(ENABLE_SERVICE), ENABLE_SERVICE_DEFAULT);
    }
}
