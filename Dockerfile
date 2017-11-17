FROM relateiq/oracle-java8

RUN apt-get update && \
    apt-get install -y git && \
    git clone https://github.com/joshjs1991/onandoffandonandoff.git && \
    cd onandoffandonandoff && \
    ./gradlew build

EXPOSE 8080

ENTRYPOINT java -jar ./onandoffandonandoff/build/libs/onandoff-0.1.0.jar
