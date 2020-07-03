# Banking Management Application
Application with web view and rest services to support management of customer accounts and transfer inter account transfer.
		
## Base package
```text
com.citi.banking.BankingManagement
```

## Project Setup
### Technology Stack 
```text
1. Maven 3
2. Java 8
3. Spring Boot v2.3
4. Spring Webflux
5. Swagger Open API 3
6. Mockito
7. jQuery
8. Twitter Bootstrap
9. HTML
10. CSS
```

## Rest Endpoints
This project uses Spring Data Rest and hence all entities the CRUD operations of the entities are exposed
as REST API following REST API Best practices.

### To get list of REST API URLs for all entities(Customer and Account) 
http://localhost:8080/
```json
{
  "_links" : {
    "customers" : {
      "href" : "http://localhost:8080/customers"
    },
    "accounts" : {
      "href" : "http://localhost:8080/accounts"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile"
    }
  }
}
```

### Account APIs

#### Read: To get all accounts for all customers go to http://localhost:8080/accounts
```json
{
  "_embedded" : {
    "accounts" : [ {
      "type" : "CHECKING",
      "amount" : 3200.00,
      "nickName" : "My Checking",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/accounts/1"
        },
        "account" : {
          "href" : "http://localhost:8080/accounts/1"
        },
        "customer" : {
          "href" : "http://localhost:8080/accounts/1/customer"
        }
      }
    }, {
      "type" : "MONEY_MARKET",
      "amount" : 4500.00,
      "nickName" : "Stock Account",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/accounts/2"
        },
        "account" : {
          "href" : "http://localhost:8080/accounts/2"
        },
        "customer" : {
          "href" : "http://localhost:8080/accounts/2/customer"
        }
      }
    }, {
      "type" : "SAVINGS",
      "amount" : 300.00,
      "nickName" : "My Saving",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/accounts/3"
        },
        "account" : {
          "href" : "http://localhost:8080/accounts/3"
        },
        "customer" : {
          "href" : "http://localhost:8080/accounts/3/customer"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/accounts"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/accounts"
    },
    "search" : {
      "href" : "http://localhost:8080/accounts/search"
    }
  }
}
```
#### Create: For create post the following JSON to http://localhost:8080/accounts
```json
{
                "type": "CHECKING",
                "amount": 999,
                "nickName": "My Checking account"
	
}
```
In case of validation error a 206 will be issued. For example below request will result in a 206 as accounts nickname is
reburied field.
```text
Field error in object 'Account' on field 'nickName': rejected value []; codes [nickName.empty.Account.nickName,nickName.empty.nickName,nickName.empty.java.lang.String,nickName.empty]; arguments []; default message [null]
```


### Customer APIs
#### Read: To get all accounts for all customers go to http://localhost:8080/accounts
```json
{
    "_embedded": {
        "customers": [
            {
                "firstName": "John",
                "lastName": "Doe",
                "address": "123 Hollow Street",
                "phoneNumber": "898099321",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/customers/1"
                    },
                    "customer": {
                        "href": "http://localhost:8080/customers/1"
                    },
                    "accounts": {
                        "href": "http://localhost:8080/customers/1/accounts"
                    }
                }
            },
            {
                "firstName": "Smith",
                "lastName": "Thomson",
                "address": "232 Hidden Street",
                "phoneNumber": "823099321",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/customers/2"
                    },
                    "customer": {
                        "href": "http://localhost:8080/customers/2"
                    },
                    "accounts": {
                        "href": "http://localhost:8080/customers/2/accounts"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/customers"
        },
        "profile": {
            "href": "http://localhost:8080/profile/customers"
        }
    }
}
```

