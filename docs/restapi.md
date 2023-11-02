# SuiteSpot REST Server - API Documentation


## `Endpoints`
Endpoints are exposed to `localhost:8080`


## `customer`


<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /customer </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary"> 
        <ul>
            <li><strong>Description:</strong> Retrieves a list of all customers in the system. </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK)
                    <li>JSON Array containing a list of Customer objects</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /customer/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary">
        <ul>
            <li><strong>Description:</strong> Retrieves a customer by their unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the customer to retrieve (UUID).</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK) if the customer is found, 404 (Not Found) if the customer does not exist.</li>
                    <li>JSON object representing the Customer if available.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="post-summary"> 
        <h2 class="post verb"> POST</h2> 
        <h2 class="endpoint"> /customer </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="post-summary">
        <ul>
            <li><strong>Description:</strong> Creates a new customer or updates an existing one.</li>
            <li><strong>Request Body:</strong>
                <ul>
                    <li>JSON object representing the Customer to be created or updated.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 201 (Created) if the customer is successfully created or updated.</li>
                    <li>HTTP Status Code: 400 (Bad Request) if there is an issue with the request data (e.g., invalid input).</li>
                    <li>String containing customer-id or an error message.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="delete-summary"> 
        <h2 class="delete verb"> DELETE</h2> 
        <h2 class="endpoint"> /customer/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="delete-summary">
        <ul>
            <li><strong>Description:</strong> Deletes a customer by their unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the customer to delete (UUID).</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 204 (No Content) if the customer is successfully deleted.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>

---

## `booking`
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /booking </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary">
        <ul>
            <li><strong>Description:</strong> Retrieves a list of all bookings in the system.</li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK)</li>
                    <li>JSON Array containing a list of Booking objects.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /booking/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary">
        <ul>
            <li><strong>Description:</strong> Retrieves a booking by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id}: The ID of the booking to retrieve (UUID).</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK) if the booking is found, 404 (Not Found) if the booking does not exist.</li>
                    <li>JSON object representing the Booking if available.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="post-summary"> 
        <h2 class="post verb"> POST</h2> 
        <h2 class="endpoint"> /booking</h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="post-summary">
        <ul>
            <li><strong>Description:</strong> Creates a new booking or updates an existing one.</li>
            <li><strong>Request Body:</strong>
                <ul>
                    <li>JSON object representing the Booking to be created or updated. </li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 201 (Created) if the booking is successfully created or updated.</li>
                    <li>HTTP Status Code: 400 (Bad Request) if there is an issue with the request data (e.g., invalid input).</li>
                    <li>String containing booking-id or an error message.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="delete-summary"> 
        <h2 class="delete verb">DELETE</h2> 
        <h2 class="endpoint"> /booking/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="delete-summary">
        <ul>
            <li><strong>Description:</strong> Deletes a booking by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id}: The ID of the booking to delete.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 204 (No Content) if the booking is successfully deleted.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>

---

## `room`
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /room </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary"> 
        <ul>
            <li><strong>Description:</strong> Retrieves a list of all rooms in the system. </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK)</li>
                    <li>JSON Array containing a list of Room objects</li>
                </ul>
            </li>
        <ul>
    </div>
</details>
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /room/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary">
        <ul>
            <li><strong>Description:</strong> Retrieves a room by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the room to retrieve (UUID).</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK) if the room is found, 404 (Not Found) if the room does not exist.</li>
                    <li>JSON object representing the Room if available.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="post-summary"> 
        <h2 class="post verb"> POST</h2> 
        <h2 class="endpoint"> /room </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="post-summary">
        <ul>
            <li><strong>Description:</strong> Creates a new room or updates an existing one.</li>
            <li><strong>Request Body:</strong>
                <ul>
                    <li>JSON object representing the Room to be created or updated.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 201 (Created) if the room is successfully created or updated.</li>
                    <li>HTTP Status Code: 400 (Bad Request) if there is an issue with the request data (e.g., invalid input).</li>
                    <li>String containing room-id or an error message.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="delete-summary"> 
        <h2 class="delete verb"> DELETE</h2> 
        <h2 class="endpoint"> /room/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="delete-summary">
        <ul>
            <li><strong>Description:</strong> Deletes a room by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the room to delete.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 204 (No Content) if the room is successfully deleted.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>

---
## `roomtype`

<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /roomtype </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary"> 
        <ul>
            <li><strong>Description:</strong> Retrieves a list of all room types in the system. </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK)</li>
                    <li>JSON Array containing a list of RoomType objects</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="get-summary"> 
        <h2 class="get verb"> GET</h2> 
        <h2 class="endpoint"> /roomtype/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="get-summary">
        <ul>
            <li><strong>Description:</strong> Retrieves a room type by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the room type to retrieve (UUID).</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 200 (OK) if the room type is found, 404 (Not Found) if the room type does not exist.</li>
                    <li>JSON object representing the RoomType if available.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="post-summary"> 
        <h2 class="post verb"> POST</h2> 
        <h2 class="endpoint"> /roomtype </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="post-summary">
        <ul>
            <li><strong>Description:</strong> Creates a new room type or updates an existing one.</li>
            <li><strong>Request Body:</strong>
                <ul>
                    <li>JSON object representing the RoomType to be created or updated.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 201 (Created) if the room type is successfully created or updated.</li>
                    <li>HTTP Status Code: 400 (Bad Request) if there is an issue with the request data (e.g., invalid input).</li>
                    <li>String containing roomtype-id or an error message.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>
<details>
    <summary class="delete-summary"> 
        <h2 class="delete verb"> DELETE</h2> 
        <h2 class="endpoint"> /roomtype/{id} </h2>
        <h2 class="dropdown"> ▼</h2>
    </summary>
    <div class="delete-summary">
        <ul>
            <li><strong>Description:</strong> Deletes a room type by its unique ID.</li>
            <li><strong>Parameters:</strong>
                <ul>
                    <li>{id} (Path Parameter): The ID of the room type to delete.</li>
                </ul>
            </li>
            <li><strong>Response:</strong>
                <ul>
                    <li>HTTP Status Code: 204 (No Content) if the room type is successfully deleted.</li>
                </ul>
            </li>
        </ul>
    </div>
</details>













<style>

    summary {
        display: flex;
        flex-direction: row;
        gap: 10px;
        cursor:pointer;
        user-select:none;
        border-radius:5px;

        margin: 10px 0px;
        padding: 10px;
    }

    h2 {
        margin: 0px;
    }

    li{
        color:#000;
    }

    div{
        padding:10px;
        border-radius:5px;
    }

    .post-summary{
        background-color:#eaf6f0;
        border: 3px solid #5bca93;
    }

    .delete-summary{
        background-color:#fae8e8;
        border: 3px solid #f24845;
    }

    .get-summary{
        background-color:#ecf3fa;
        border: 3px solid #68aefa;
    }

    .post{
        background-color:#5bca93;
    }

    .delete{
        background-color:#f24845;
    }

    .get{
        background-color:#68aefa;
    }
    
    .verb{
        border-radius:5px;
        padding: 10px;
        width: 80px;
        text-align: center;
    }

    .endpoint{
        color:#000;
        padding:10px;
        border-bottom: 0;
    }
    .dropdown{
        color:#000;
        padding:10px;
        border-bottom: 0;
        margin-left: auto;
    }

</style>
