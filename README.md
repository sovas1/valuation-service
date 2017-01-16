# Description

Input:
* data.csv - product representation with price,currency,quantity,matching_id
* currencies.csv - currency code and ratio to PLN, ie. GBP,2.4 can be converted to PLN with procedure 1 PLN * 2.4
* matchings.csv - matching data matching_id,top_priced_count

Now, read all the data. From products with particular matching_id take those with the highest total price (price * quantity), limit data set by top_priced_count and aggregate prices. 
Result save to top_products.csv with four columns: matching_id,total_price,avg_price,currency,ignored_products_count.



# Docker


Docker image available on:
[https://hub.docker.com/r/sovas/valuation-service/](https://hub.docker.com/r/sovas/valuation-service/)

To start docker container type:
* docker pull sovas/valuation-service
* docker run sovas/valuation-service


To build your own docker image localy just type:
* mvn docker:build

Remember - you need to have docker environment variables exported.
