# Shopping platform 

## Requirements
- installed [docker](https://www.docker.com/)

## Run
```bash
# run postgres instance
docker compose up -d 

#run the app
mvnw spring-boot:run
```

Application should be ready to go! 

## Functionalities
### Core
`POST /v1/order/preview` - calculate a price for a product specified in body including quantity and discounts

### Helpers
- `POST /v1/product` - allows to create a product 
- `POST /v1/discount` - allows to create a discount (percentage or quantity based)
- `GET /v1/product/{id}` - get a product details 
- `DELETE /v1/product/{id}` - remove a product
- `GET /v1/product` - get all products

Each endpoint is listed in postman collection included in folder named `postman` in root directory. Feel free to play around!



