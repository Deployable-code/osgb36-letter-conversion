osgb36-letter-conversion
========================

[![Java Version](http://img.shields.io/badge/Java-1.8-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![Maven Version](http://img.shields.io/maven-central/v/ro.ghionoiu/osgb36-letter-conversion.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22ro.ghionoiu%22%20AND%20a%3A%22osgb36-letter-conversion%22)
[![Build Status](https://travis-ci.org/Deployable-code/osgb36-letter-conversion.svg?branch=master)](https://travis-ci.org/Deployable-code/osgb36-letter-conversion)
[![Coverage Status](https://img.shields.io/coveralls/julianghionoiu/osgb36-letter-conversion.svg)](https://coveralls.io/r/julianghionoiu/osgb36-letter-conversion?branch=master)

 A library to convert OSGB36 Eastings and Northings into grid letter representation. It is based on the information from 
 this [Wikipedia page](http://en.wikipedia.org/wiki/Ordnance_Survey_National_Grid).

Transforms OSBG36 coordinates:
```
Eastings   255940 metres.
Northings   98127 metres.   
```

To UK National Grid Reference:
```
SX59   (10Km)
SX5598  (1Km)
```
The precision can be configured down to 1 meter

### How to get it

Maven users, add this to your POM:
```xml
<dependency>
    <groupId>ro.ghionoiu</groupId>
    <artifactId>osgb36-letter-conversion</artifactId>
    <version>0.1.3</version>
</dependency>
```


### Usage

```java
OsgbPointToReferenceConverter converter =
        new OsgbPointToReferenceConverter(Reference.BOX_10km);

int eastings = 255940;
int northings = 98127;
System.out.println(converter.toGridReference(eastings, northings));

//Will output: SX59
```

### Need help ?

If you encounter any problems or you have a feature request please open an issue on this project's Github.

### References

* http://en.wikipedia.org/wiki/Ordnance_Survey_National_Grid
* http://www.freemaptools.com/find-british-national-grid-reference-from-map.htm
* http://www.gridreferencefinder.com
