# onyx-java

Onyx-Java provides a simple Java interface for the Onyx Platform's core API, utilities for manipulating Clojure maps directly in Java, tools to simplify use of core.async plugins, and affordances for inclusion of pure Java classes in a workflow.   <br>
<br>

## Overview 
Onyx-Java follows the Onyx Platform's core API providing Java peer's for each aspect of a workflow. I.e. Catalogs, Lifecycles, Jobs, etc. <br>
<br>
These classes provide methods to add entries to your workflow description ensuring that they are converted into Clojure-native types when needed. Note that this approach doesn't validate semantic correctness of your entries, which are enforced at runtime.<br>
<br>
### Utilities

#### Maps

A utility class, MapFns, offers Java-esk versions of (some) Clojure map munipulation functions, like get-in, making it easier to directly manipulate Clojure map's. It also provides support for loading edn files of simple maps from resources making it easier to manage entries like environment and peer configuration. <br>
<br>

#### Core Async

Support for the use of core.async plugins are provided via a pair of Java classes. AsyncCatalog and AsyncLifecycles encapsulate generating the correct catalog and lifecycle entries, as well as providing support methods for use during job runtime to pass and collect data.<br>
<br>

#### Java Objects

Support for use of pure Java objects in a workflow is provided via an abstract base class, OnyxMethod, along with a catalog generation tool CatalogUtils. <br>
<br>
*OnyxMethod* is an abstract base class with a constructor that takes a Clojure map, and an abstract method that consumes the Clojure map segment. *CatalogUtils* provides a means to create an instance-aware catalog entry that calls your bootrapped derived instance at runtime.<br>
<br>

## Usage

### Basic 

### Java Objects 


## License

Copyright © 2016 Distributed Masonry

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.


#### NOTE: This repo is under active development and not ready for actual usage yet.

