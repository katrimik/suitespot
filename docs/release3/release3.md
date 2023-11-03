# Release 3



## Table of contents
- [Release 1-3](#release-3)
  - [Table of contents](#table-of-contents)
  - [Introduction](#introduction)
    - [FileTypeEnum](#filetypeenum)
    - [JsonFile](#jsonfile)
  - [Customer Interface](#customer-interface)
    - [Customer](#customer)
    - [CustomerManager](#customermanager)
  - [Room Type and Room Interface](#room-type-and-room-interface)
    - [RoomType](#roomtype)
    - [RoomTypeManager](#roomtypemanager)
    - [Room](#room)
    - [RoomManager](#roommanager)
  - [Booking](#booking)
  - [Testing](#testing)
  - [Functionality](#functionality)
  - [Checkstyle warnings and spotbugs](#checkstyle-warnings-and-spotbugs)

## Introduction
In the first release ( _Release 1_ ) we have created the Java-files in headers _"Introduction"_ and _"Customer-Interface"_. For the following release ( _Release 2_ ), we have implemented Java-files under _"Room"_. In the last release (_Release 3_) we have completed the final files and we have a "fully functional" booking-app, were the newly implemented files for booking lies under _"Booking"_. In _"Test"_ and _"Functionality"_, we have implemented files accordingly to the appropriate release.

### FileTypeEnum
You can find `FileTypeEnum` in the _core/fileUtil_-folder: this is were we handle our file-management.

Enumerations in general defines a set of named constants. In our case, `FileTypeEnum` uses the constants "CUSTOMER", "BOOKING" and "ROOM". This is helpful when we create the booking- and room-classes.

### JsonFile
The `JsonFile` can also be found in the _core/fileUtil_-folder.

The purpose of the file is to create an instance with the class you want to save, and the name of the file you want to save to. The JsonFile:
- appends to file
- writes to file
- reads to file


## Customer Interface

### Customer 
In the _model_ folder, you will discover the `Customer` file, which serves as a data object for managing individual customers. This file includes various validation methods utilizing regular expressions.

### CustomerManager
In the _manager_ folder, `CustomerManager` is located. Here is the customer-ID generated, and consists of logic for saving, creating, editing and deleting customers. 

## Room Type and Room Interface
### RoomType
In the folder _model_, you'll find the `RoomType`-file, which is a data-object. Sorting the rooms into different room types makes it easier for the manager to find the correct price and size based on the customers' preferences. 

### RoomTypeManager
In the `RoomTypeManager`-file, you generate the room type-ID for saving, creating, editing and deleting room types. Additionally, you can verify the availability of a room type to prevent duplicates.

### Room
`Room` is also situated in the _model_ folder, serving as a data object to manage individual rooms. Each room is assigned a unique ID, along with a room number and a specific room type. A room is reserved for a booking, ensuring a one-to-one correspondence between bookings and rooms.

### RoomManager
In the _manager_ folder, you'll locate `RoomManager` where you can generate a room  ID, as well as save, create, and delete rooms. Moreover, it provides the functionality to check the availability of a room.

## Booking

### Booking
Finally, the `Booking` file is situated in the model folder. It serves as a data object, capturing information related to bookings. When initiating a booking, you begin by selecting the desired dates, choosing an available room type, and then specifying the customer's name.

### BookingManager
In the _manager_ folder, you'll find the `BookingManager` file, where you can generate an ID for creating, saving, and deleting bookings.

## Testing
In the first release, testing was minimal due to the limited amount of code logic. However, we focused on testing essential core functionalities. Specifically, we conducted tests on various validation methods within the `Customer` file, resulting in a test coverage of 80%.

In the second release, we delved into testing the core logic of rooms, encompassing the `Room` and `RoomType` files along with their respective managers. Additionally, we expanded customer testing to incorporate the `CustomerManager`, achieving an test coverage of 91%. Furthermore, we complemented these efforts with UI tests for both `CustomerController` and `RoomController`, resulting in a test coverage of 79%.

In the latest and third release, the architecture has become more comprehensive, necessitating additional tests. Consequently, we have executed tests for the remaining files in both core and UI. Tests have been conducted for core _model_-files, namely `Customer` and `Booking`, resulting in a cumulative test coverage of 86%. Additionally, for _manager_-files such as `RoomManager`, `RoomTypeManager`, and `BookingManager`, the test coverage is also 86%.

Regarding UI tests, we have implemented tests for the remaining controllers `CreateBookingController` and  `AppController`, achieving an overall test coverage of 83%.

The fileUtil package remains untested as it relies on an external system, namely the file system. To address this, the test package incorporates a mock implementation that utilizes memory to test classes dependent on fileUtil.

Contrastingly, UI-utils have undergone testing, achieving a test coverage of 82%.

![jacoco-core-r3.png](../image/release3/jacoco-core-r3.png)
![jacoco-ui-r3.png](../image/release3/jacoco-ui-r3.png) 


For full report see [jacoco](../../suitespot/core/target/site/jacoco/index.html) after running: 
```
mvn verify
```

## Functionality
`Suitespot` enables you to access the main page through the layout-user interface. Once there, you have the option to navigate to either _"Manage Customer"_ or _"Manage Rooms"_. Additionally, we have implemented a feature that provides an overview of booked dates. These dates are linked to a room, room type, and a customer, and the details are displayed upon clicking the booking. The list view of bookings allows you to sort them based on your preference using radio buttons. To locate a specific booking, you can scroll through the list or use the search bar above. Furthermore, you have the ability to delete a selected booking by marking it. 

![main-booking.png](../image/release3/main-booking.png) 

When you click on _"Manage customers"_, you get to create, edit and delete customers, from the customer-user-interface. If you wish to return to main page, you can click on that spesific button.

![r2-customer.png](../image/release2/r2-customer.png)

Furthermore, by selecting _"Manage rooms"_ from the main page, you gain access to features that enable you to create, edit, and delete room types, specifying names and prices through the room-user interface. After establishing a room type, you can proceed to create, edit, or delete specific rooms within that category. Additionally, the system allows you to switch a room's type to another pre-existing room type.

![manageRooms.png](../image/release2/manageRooms.png) 


## Checkstyle warnings and spotbugs
While we still encounter several checkstyle warnings, the majority pertain to javadoc. We've diligently included javadoc for most public-facing methods in the core, except for those that are inherently self-explanatory, such as getters and setters. Both documented and undocumented methods are aptly named. Although there are additional warnings in checkstyle, we've encountered challenges linking checkstyle to the config file, hindering us from adjusting preferences due to differences in coding styles.

On a positive note, our spotbugs analysis has yielded zero errors, reflecting a more extensive and meticulous testing process. As a result, we've prioritized our focus on spotbugs testing.