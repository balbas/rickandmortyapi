# PRACTICAL EXERCISE

API Rest application with Spring Boot for using the [Rick and Morty API](https://rickandmortyapi.com/documentation).

To test this API, a [Postman file](rickandmortyapi.postman_collection.json) is included with the GET request to be made:

```
GET http://localhost:8081/search-character-appearance?name=Rick+Sanchez
```

As a result, we will obtain a JSON with the following field structure:

```
{
    "name": "Rick Sanchez",
    "episodes": [
        "Pilot",
        "Lawnmower Dog",
        "Anatomy Park",
        "M. Night Shaym-Aliens!",
        "Meeseeks and Destroy",
        "Rick Potion #9",
        "Raising Gazorpazorp",
        "Rixty Minutes",
        "Something Ricked This Way Comes",
        "Close Rick-counters of the Rick Kind",
        "Ricksy Business",
        "A Rickle in Time",
        "Mortynight Run",
        "Auto Erotic Assimilation",
        "Total Rickall",
        "Get Schwifty",
        "The Ricks Must Be Crazy",
        "Big Trouble in Little Sanchez",
        "Interdimensional Cable 2: Tempting Fate",
        "Look Who's Purging Now",
        "The Wedding Squanchers",
        "The Rickshank Rickdemption",
        "Rickmancing the Stone",
        "Pickle Rick",
        "Vindicators 3: The Return of Worldender",
        "The Whirly Dirly Conspiracy",
        "Rest and Ricklaxation",
        "The Ricklantis Mixup",
        "Morty's Mind Blowers",
        "The ABC's of Beth",
        "The Rickchurian Mortydate",
        "Edge of Tomorty: Rick, Die, Rickpeat",
        "The Old Man and the Seat",
        "One Crew Over the Crewcoo's Morty",
        "Claw and Hoarder: Special Ricktim's Morty",
        "Rattlestar Ricklactica",
        "Never Ricking Morty",
        "Promortyus",
        "The Vat of Acid Episode",
        "Childrick of Mort",
        "Star Mort: Rickturn of the Jerri",
        "Mort Dinner Rick Andre",
        "Mortyplicity",
        "A Rickconvenient Mort",
        "Rickdependence Spray",
        "Amortycan Grickfitti",
        "Rick & Morty's Thanksploitation Spectacular",
        "Gotron Jerrysis Rickvangelion",
        "Rickternal Friendshine of the Spotless Mort",
        "Forgetting Sarick Mortshall",
        "Rickmurai Jack"
    ],
    "first_appearance": "2013-12-02"
}
```

To get the required data format, first get the characters whose name exactly matches the name passed as parameter. If none exists, a controlled exception is returned.
For each character (in this case, it will always be a single character), we make a request to get the information of the episodes in which it appears. 

For both compiling and running the tests, you can use [Maven](https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip).

The command to run the tests is as follows:

```
mvn test
```

The command to compile the application is as follows (this command also runs the tests):

```
mvn clean package
```

To start the server and be able to test locally, we execute the following command:

```
java -jar target/rickandmortyapi-0.0.1-SNAPSHOT.jar
```

The project has the [Dockerfile](Dockerfile) to generate the image and to run the project in a container.
It also includes the [image](rickandmortyapi-docker.tar) already generated to deploy directly on a computer.

## License

Check [here](LICENSE).