ProductAPI
===================

Swagger is used to document the APIs and call them.
http://<localhost:8080>/ProductAPI/swagger-ui.html


## GET Product by Id
	http://<localhost:8080>/ProductAPI/products/id/<productId>

  Returns 404 if the id does not exist.
  The Product Price is stored in a local storage and product Name is from another rest resource. The get API call combines the price and name and returns the Product details.
  If neither are provided it throws a HttpStatus.NOT_FOUND 404 error.

  The response is JSON of the format
  {
  "id": 13860428,
  "price": {
    "amount": 225,
    "currency": "USD"
  },
  "name": "The Big Lebowski (Blu-ray)"
}

## PUT update existing Product resource
	http://<localhost:8080>/ProductAPI/products/id/<productId>
	Request JSON
  {
  "id": <productId>,
  "price": {
    "amount": 33.00,
    "currency": "USD" (This is optional, if not provided it is considered USD by default.)
  }
}

If the resource does not exist a HttpStatus.NOT_FOUND 404 is returned.

## POST add new Product
Adds a new product. Currently this also updates the resource if it already exists,
	http://<localhost:8080>/ProductAPI/products
	Request JSON
  {
  "id": 33,
  "price": {
    "amount": 33.00,
    "currency": "USD" (This is optional, if not provided it is considered USD by default.)
  }
}

# POC and API Reference
This POC is micro service to manage product information

DNS your domain name(IP address) and port number
API information available in swagger document  "http://localhost:8080/rest-springboot/swagger-ui.html"
*Note : Change DNS name to server DNS

# build instructions
gradlew build

# run application
gradle bootrun


## Contributors
Tanya Simhadri

## License
@CopyRight ( C ) All rights reserved . Feel free to use it as needed if it helps you.
