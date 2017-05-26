package com.nateyolles.aem.osgiannotationdemo.core.servlets;

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
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.nateyolles.aem.osgiannotationdemo.core.services.SampleOsgiService;

@Component(
    immediate = true,
    service = Servlet.class,
    property = {
        "sling.servlet.extensions=txt",
        "sling.servlet.paths=/bin/osgi",
        "sling.servlet.paths=/bin/foo",
        "sling.servlet.methods=get"
    },
    configurationPid = "com.nateyolles.aem.osgiannotationdemo.core.servlets.SampleOsgiServlet"
)
@Designate(ocd=SampleOsgiServlet.Configuration.class)
public class SampleOsgiServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private SampleOsgiService sampleOsgiService;

    private boolean enabled;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        resp.setContentType("text/plain");
        out.write("Annotation Demo Servlet - OSGi - enabled: " + enabled + "\n");
        out.write(sampleOsgiService.getSettings());
    }

    @Activate
    @Modified
    protected void Activate(Configuration config) {
        enabled = config.enabled();
    }

    @ObjectClassDefinition(name = "Annotation Demo Servlet - OSGi")
    public @interface Configuration {
        @AttributeDefinition(
            name = "Enable",
            description = "Sample boolean property"
        )
        boolean enabled() default false;
    }
}
