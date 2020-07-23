# interest

## Environment Assumptions

* Java 8
* Springboot 2.3.1, jar packaging
* Gradle 6.4

## Non-functional Assumptions

* All components running locally, single user mode
* No need to distinguish users
* No security requirements (http, mongo credentials stored in a local config file)

## Functional Assumptions

* Currency used will be GBP / USD like (whole units, 100 subunits), but not declared as any specific currency
* Interest rates can be positive, zero, and negative integers
* There can be no gaps or overlaps in the interest rate bands

## Tools used

* Springboot Initializr
* Mongo

## Design

### Back End

* 3 Tier approach
  * REST controller
    * Submit values for calculation
    * Submit values for persisting
    * Retrieve all persisted values
    * Clear all persisted values (required for testing)
  * Business logic service
    * Public methods for the above
    * Private / protected methods as required
  * Data store repository
    * Methods as needed for persisting and retrieving
* Data Model
  * List of interest rate bands
    * eg 1000 (lower bound) to 5000 (upper bound) gains 2% (interest rate) 
  * Amounts of earned interest calculated  