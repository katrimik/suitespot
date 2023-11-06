<style>
h1 { border-bottom: 0.5; }
cite { font-size: 10px}
 </style>


# User stories
Purpose: *We collect user stories with the purpose of (un)covering the requirements the system must meet.
There are various forms of user stories, with more or less strict requirements about their form. Here, we try to summarize each of them somewhat structured.*


## Table of contents

- [Add a new customer (US-1)](#new-customer-wants-to-book-a-room-us-1)
- [Edit customer details (US-2)](#edit-customer-details-us-2)
- [Create new room and roomtype (US-3)](#create-new-room-and-room-type-us-3)
- [Create new booking (US-4)](#create-new-booking-us-4)

## Add a new customer (US-1)

*A new customer has arrived at the hotel and wishes to book a room. As a receptionist at a hotel, my first step is to register the customer as a new customer.*

The customer wanting to book a room has not visited the hotel before, so the staff must create a new "customer" in the hotel system. The staff must fill in details such as first name, last name, phone number, and email.

### Important to be able to view

- Overview: To see which customers already exist.
- To identify the required fields for creating a new user.
- To receive a warning if attempting to enter invalid data (e.g., incorrect email format).

### Important to be able to do

- Add new customers.
- Select existing customers and obtain information about them.
- "Reuse" existing customers.

## Edit Customer Details (US-2)

*It turns out that the customer who has been registered provided an incorrect phone number (it can happen to the best of us). As an employee, I should be able to access and modify the phone number for this customer.*

The receptionist needs the ability to navigate to an existing customer and make changes to the desired data field, in this case: the phone number. It's crucial to save the changes and revisit the customer to double-check that the modification has been updated.

### Important to be able to view

- Previously registered customers.
- Existing data associated with the customer.

### Important to be able to do

- Modify the provided data for the respective customer.
- Save the changes.

## Create new room and room type (US-3)
*The hotel has been expanded! As a hotel employee, I now want to register a new room type, namely the "Bridal Suite." This room will cost 10 000 kr. We have one room of this type, and the room number is 303.*

An employee should have the ability to create a new room type, assign a price to it, and register a new room of this type. One requirement is that you must specify the room number.

### Important to be able to view

- An overview of all existing rooms and room types.
- A warning if the room type already exists.
- A warning if the room number is already in use.

### Important to be able to do

- Create a new room type.
- Create a new room of the new room type.
- The ability to modify the data associated with the room type, such as the price.
- Delete both rooms and room types.

# Create new booking (US-4)
*The customer who was just registered has recently gotten married! Congratulations! The married one now wants to book the new room, the "Bridal Suite."*

As an employee, you should now book the new room: "Bridal Suite." The customer already exists in the system, so the employee needs to be able to navigate to the customer in the system and book the "Bridal Suite" on the customer's name and with the desired time period.

### Important to be able to view
- Create button for bookings.
- Date picker view for dates.
- Existing and available rooms (display room numbers).
- Customer view.
- Distinguish the view you are currently editing (difference in opacity).


### Important to be able to do

- Create a booking with the following data:
  - Date
  - Room
  - Customer
- Navigate between data input fields (in case of regrets).
- Save the booking.
- Cancel the booking.