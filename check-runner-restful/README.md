

        Spring Boot Rest project 
    ___________________________________________________________________________________________________________________

        Technology Used

    Spring Boot
    PostgreSQL  database
    Hibernate-validator
    Lombok
    Mockito
    ____________________________________________________________________________________________________________________
    
        How to start this project

    Create database clevertec
    import schema.sql and data.sql
    ____________________________________________________________________________________________________________________

        Interaction with the service

    Get all available products for order
    http://localhost:8088/api/allProducts
    ____________________________________________________________________________________________________________________

    To receive a check, we use the post method. 
    In the body, we pass the id and the quantity of the product. The discount card number is passed in the url
    http://localhost:8088/api/check?cardNumber=1235
    [
        {"id":1 , "count": 2},
        {"id":2 , "count": 1},
        {"id":4 , "count": 2}
    ]
    ____________________________________________________________________________________________________________________

    Get product by id
    http://localhost:8088/api/product/{id}
    ____________________________________________________________________________________________________________________

    Save product or card
    http://localhost:8088/api/save/product
    http://localhost:8088/api/save/card
    ____________________________________________________________________________________________________________________

    Update product or card
    http://localhost:8088/api/save/product
    http://localhost:8088/api/save/card
    ____________________________________________________________________________________________________________________

    Delete product or card by id
    http://localhost:8088/api/product/{id}
    http://localhost:8088/api/card/{id}
    ____________________________________________________________________________________________________________________
    