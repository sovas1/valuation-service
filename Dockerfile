FROM anapsix/alpine-java:8
COPY target/valuation-service-1.0.jar /maven/
COPY src/main/resources/ /maven/resources/
CMD java -jar /maven/valuation-service-1.0.jar org.sovas.Application