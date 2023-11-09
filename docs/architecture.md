
## Document metaphor

Saving to file happens when we click the save or delete buttons. If the app is closed while editing, it will not save. 

---

## Architecture
There are 4 modules in the system. They call each other as follows: `ui.controller -> core.apiManager ->(http) restserver -> core.manager -> core.fileUtil`.

This is an unconventional way to do it, it would make more sense to have this as two separate projects, one for frontend and one for backend, but this wasn't the setup used in the subject IT1901.

The frontend controllers handle the state. The apiManagers and the managers implement the same interfaces, so it's easy to replace them. The REST-API wasn't developed before release 3, and without separate projects it will feel like an ad-hoc architecture.

One very great decision we took was to use generic classes for our connection to the file system and the connection the API. This saved us a lot of duplicate code. 

## Format:

Userdata is stored in multiple files:
- customer.json 
- room-type.json 
- room.json 
- booking.json

It is therefore possible to read the data seperately.

---

### customer.json

This file contains information about each customer:

```json
[
    {
        "firstName":"John",
        "lastName":"Smith",
        "id":"f73bee33-a1c3-4530-b0ed-92673ad75087",
        "email":"john.smith@example.com",
        "phone":"99887701"
    }
]
```
---

### room-type.json

This file contains information about each room-type:

```json
[
    {
        "id":"99776655-6be9-4961-85a9-11fcf01e34fa",
        "name":"Suite",
        "price":10000
    }
]
```

The file is structured as an array with room-type objects. Each room-type object has an id, a price and a name.

This means that all Rooms of a certain type will cost the same. If two rooms need different pricing, they must be of different types.

---


### room.json

This file contains information about each room:

```json
[
    {
        "id":"f73bee33-a1c3-4530-b0ed-92673ad75087",
        "roomNumber":101,
        "typeId":"99776655-6be9-4961-85a9-11fcf01e34fa",
        "bookedDays": []
    }
]
```

Each room has a roomtype, which is referenced by the typeId. 
We can therefore check a rooms price, or name(type-name) by using the typeId. 

There is also a list of booked days in each room. This contains strings in the format "yyyy-mm-dd". This makes it possible to check if a room is avaialable or not. 


---

### booking.json
```json
[
  {
    "id": "71420378-e5af-4d24-a865-0457be127d81",
    "roomId": "3a34eb82-e8f4-4809-9154-d0f8f734f49e",
    "customerId": "f73bee33-a1c3-4530-b0ed-92673ad75087",
    "fromDate": "2023-11-02",
    "toDate": "2023-11-03"
  }
] 
```

Each booking is connected to a room, a customer and has a from and to -date. fromDate and toDate is formatted like "yyyy-mm-dd" (using a custom LocalDate serializer).