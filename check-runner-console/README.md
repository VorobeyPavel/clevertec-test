

        Technology Used

    Spring 
    PostgreSQL  database
    Lombok
    ____________________________________________________________________________________________________________________
    
        How to start this project

    Create database clevertec
    import schema.sql and data.sql
    ____________________________________________________________________________________________________________________

    The application is launched with a set of parameters in the itemId-quantity format 
    (itemId is the product identifier, quantity is its quantity. 
    Sample input: 3-1 2-5 5-1 card-1234

    In the application, you can choose where to get the data. 
    From a database, a collection in the DaoImpl class, or read from a file. 
    The default option is to read from a database.

    The file must store data in the following format: id-nameProduct-price (1-Avocado-7.99). 
    where id is the item number in the warehouse. nameProduct - product name. price - price.

    There are three options for printing a check (console, file and email). 
    Three options can work simultaneously. 
    Three options are selected by default.
    ____________________________________________________________________________________________________________________

    