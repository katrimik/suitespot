
## Documentmetaphor

Saving to file happens when we click the save or delete buttons. If the app is closed while editing, it will not save. 

---

## Format:

Userdata is stored in multiple files:
- customer.json 
- room-type.json 
- room.json 

It is therefor possible to read them seperately instead of reading all the data everytime.

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
    },
    {
        "firstName":"Ben",
        "lastName":"Adams",
        "id":"g36baa33-r3t5-5678-b0ed-85645as93748",
        "email":"ben.adams@example.com",
        "phone":"47849506"
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
    },
    {
        "id":"aabbccdd-6se3-4221-11a4-13scf12e51gc",
        "name":"Double",
        "price":5000
    },
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
    },
    {
        "id":"g36baa33-a1c3-5678-b0ed-85645as93748",
        "roomNumber":102,
        "typeId":"aabbccdd-6se3-4221-11a4-13scf12e51gc",
        "bookedDays": ["2023-02-12", "2023-02-11","2023-11-09"]
    },
]
```

Each room has a roomtype, which is referenced by the typeId. 
We can therefore check a rooms price, or name(type-name) by using the typeId. 

There is also a list of booked days in each room. This contains strings in the format "yyyy-mm-dd". This makes it possible to check if a room is avaialable or not. 


---