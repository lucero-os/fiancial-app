# Financial app: 
## This application enables users to create accounts. Once registered, users can purchase subscriptions using a credit card through the Stripe payment gateway.
The objective of this project is to demonstrate how I would face the challenge rather than being of use.

TechStack:
SpringBoot 3.2.2,
Postgres

Requirements:
Java 17,
Docker

Setup:
For setting up DB, open command line, go to project root and run `make setup`.

Missing:  
Data validation(emails, string lengths, etc).  
Authentication and user password secure hashing (Both depend on SecurityConfig not implemented).  
User interface.  
Working payment module.  
