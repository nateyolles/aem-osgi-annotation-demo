# AEM OSGi Declarative Services Annotations

This project demonstrates the basics of using the [OSGi Declarative Services annotations](http://enroute.osgi.org/services/org.osgi.service.component.html) along side the more familiar [Felix SCR annotations](http://felix.apache.org/documentation/subprojects/apache-felix-maven-scr-plugin/scr-annotations.html).

Examples are given for both annotation styles in each of a servlet, service, filter, scheduler and an event handler / listener. These five examples are common AEM project requirements and the examples can be extrapolated out for anything else such as MBeans, Adapters, Workflow Process Steps, Replication Preprocessors, etc...

Note the service example creates the Configuration as a separate class while the other examples create the Configuration as a subclass.

## How to build

This project has been built and tested in AEM 6.2.

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

Or to deploy the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Test

Below are a some cURL commands to help you test the components. Navigate to the [Felix Configuration](http://localhost:4502/system/console/configMgr) console to update component properties.

### Servlets and Services

Make a GET request to the servlets which consume the services and respond back with plain text:

```bash
curl -u admin:admin http://localhost:4502/bin/felix
curl -u admin:admin http://localhost:4502/bin/osgi
```

* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.servlets.SampleFelixServlet](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.servlets.SampleFelixServlet)
* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.servlets.SampleOsgiServlet](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.servlets.SampleOsgiServlet)
* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.services.impl.SampleFelixServiceImpl](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.services.impl.SampleFelixServiceImpl)
* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.services.impl.SampleOsgiServiceImpl](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.services.impl.SampleFelixServiceImpl)

### Event Handler

Create a new node under `/content` and watch the logs:

```bash
curl -u admin:admin http://localhost:4502/content/foo -F"myproperty=bar"
```

### Scheduler

View the logs to see the scheduler running every 60 seconds by default.

* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleFelixScheduledTask](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleFelixScheduledTask)
* [/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleOsgiScheduledTask](http://localhost:4502/system/console/configMgr/com.nateyolles.aem.osgiannotationdemo.core.schedulers.SampleOsgiScheduledTask)

### Filters

Make a request to a resouce under `/content` and watch the logs:

```bash
curl -u admin:admin http://localhost:4502/content/geometrixx/en/products/triangle.html
```

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
