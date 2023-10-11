# Release 1



## Table of contents
1. [Introduction](#introduction)
    1. [FileTypeEnum](#filetypeenum)
    2. [JsonFile](#jsonfile)
    3. [Customer](#customer)
    4. [CustomerManager](#customermanager)
    5. [CustomerTest](#customertest)
3. [Functionality](#functionality-so-far)

## Introduction
In our first release ( _Release 1_ ) we have created the Java-files:

### FileTypeEnum
You can find `FileTypeEnum` in the _core/fileUtil_-folder: this is were we handle our file-management.

Enumerations in general defines a set of named constants. In our case, `FileTypeEnum` uses the constants "CUSTOMER", "BOOKING" and "ROOM". This is helpful when we create the booking- and room-classes.

### JsonFile
The `JsonFile` can also be found in the _core/fileUtil_-folder.

The purpose of the file is to create an instance with the class you want to save, and the name of the file you want to save to. The JsonFile:
- appends to file
- writes to file
- reads to file

### Customer 
In the folder _model_, you can find the `Customer`-file. 

The `Customer`-file is a data-object, for keeping track of one customer. There are different validation-methods, using regex.

### CustomerManager
`CustomerManager` is located in the _manager_-folder.

In `CustomerManager`, the customer-ID is created, and it consists of logic for saving, creating, editing and deleting customers. The java-file is exposed to the core-folder.

### CustomerTest
The `CustomerTest` are testing some of the different validation-methods in the `Customer`-file. We are only testing the core-functionality, were the test coverage of `Customer` is at 87%. ![jacoco-r1.png](/docs/image/release1/jacoco-r1.png) 

For full report see [jacoco](/suitespot/core/target/site/jacoco/index.html) after running:
```
mvn verify
```



## Functionality (so far)
As of now, `Suitespot` lets you create, edit and delete customers, from the customer-user-interface. ![release1UI.png](/docs/image/release1/release1UI.png)

