FROM anapsix/alpine-java:8
COPY target/valuation-service-1.0.jar /maven/
CMD java -jar /maven/valuation-service-1.0.jar org.sovas.Application