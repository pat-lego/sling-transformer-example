# Sample AEM project template

This is a project template for AEM-based applications. It is intended as a best-practice set of examples as well as a potential starting point to develop your own functionality.

## Modules

The main parts of the template are:

* [core:](core/README.md) Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* [ui.apps:](ui.apps/README.md) contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, and templates
* ui.config: contains the osgi configs for any services and for logging

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

To build all the modules and deploy the `all` package to a local instance of AEM, run in the project root directory the following command:

    mvn clean install -PautoInstallSinglePackage

Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallSinglePackagePublish

Or alternatively

    mvn clean install -PautoInstallSinglePackage -Daem.port=4503

Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

Or to deploy only a single content package, run in the sub-module directory (i.e `ui.apps`)

    mvn clean install -PautoInstallPackage

## Documentation

The build process also generates documentation in the form of README.md files in each module directory for easy reference. Depending on the options you select at build time, the content may be customized to your project.

## How it works

The SupportTransformerFactory is an OSGi service that will be used to provide access to the Sling Transformer. The transformer is defined by its pipeline.type which is exposed in the OSGi metadata of the component. 

The config is stored under /apps/slingtransformer/config/rewriter/support-rewriter which will define the generator, transformer and serializer. Currently the generator and transformer are using OOTB parsing and serialization but the the transform is custom and will force all href attributes in an anchor tag to be set to "www.google.com".

## How to install

- Start AEMaaCS Local SDK
- Clone, build and deploy the project
- Install [WKND package](https://github.com/adobe/aem-guides-wknd/releases)
- Load the [about-us](http://localhost:4502/editor.html/content/wknd/language-masters/en/about-us.html) page
- Inspect the network and look for the request to [about-us](/content/wknd/language-masters/en/about-us.html) and review the response output and validate the href for anchor tags are only set to www.google.com