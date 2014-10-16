osgb36-letter-conversion
========================

[![Java Version](http://img.shields.io/badge/Java-1.8-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![Maven Version](http://img.shields.io/maven-central/v/ro.ghionoiu/osgb36-letter-conversion.svg)](http://mvnrepository.com/artifact/ro.ghionoiu/osgb36-letter-conversion)
[![Build Status](https://travis-ci.org/julianghionoiu/osgb36-letter-conversion.svg?branch=master)](https://travis-ci.org/julianghionoiu/osgb36-letter-conversion)
[![Coverage Status](https://img.shields.io/coveralls/julianghionoiu/osgb36-letter-conversion.svg)](https://coveralls.io/r/julianghionoiu/osgb36-letter-conversion?branch=master)

 A library to convert OSGB36 Eastings and Northings into grid letter representation.

Transforms:
```
Eastings : 255940 metres.
Northings : 98127 metres.
```
Into:
```
10Km - SX59
1Km  - SX5598
```
The precision can be configured down to 1 meter

### How to get

You can get it from Maven once it updates

### Usage

```java
OsgbPointToReferenceConverter converter =
        new OsgbPointToReferenceConverter(Reference.BOX_10km);

int eastings = 255940;
int northings = 98127;
System.out.println(converter.toGridReference(eastings, northings));
```
This will output:
```
SX59
```

### Need help ?

If you encounter any problems or you have a feature request please open an issue on this project's Github.

### References

* http://en.wikipedia.org/wiki/Ordnance_Survey_National_Grid
* http://www.freemaptools.com/find-british-national-grid-reference-from-map.htm
* http://www.gridreferencefinder.com
