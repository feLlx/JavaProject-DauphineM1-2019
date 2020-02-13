# JavaProject-DauphineM1-2019

## Foreword

This java application has been developed in a school environment at Paris Dauphine University, as part of an Advanced Object Programming course of the miage master's degree.

## Description of the application

The purpose of this application is to read and write files in CSV format first, then in other data formats in a second step. The project can be run in two different ways :
* to verify the data
* to anonymize it.

The verification and anonymization rules are written in a json file.
More information on the project guidelines (in French) [here](https://github.com/emerite-neou/2019-DAUPHINE-M1/blob/master/projet/sujet.md).

## RoadMap
#### deadline : 05/03/20

* Build the project (maven project, travis, log system)
* Json file manipulation (read)
* Checking data
* Data Anonymization

The features below will be developed if we have the time

* Add more rules for verification and anonymization
* Check AND anonymize at the same time
* Consider alternative separators for csv files
* Take into account that a line does not necessarily correspond to an entry (entry on several lines or a line contains several entries),
* Consider other file types (flatfile, etc.)

## Built With

* [Eclipse](https://www.eclipse.org/) - Integrated Development Environment
* [Maven](https://maven.apache.org/) - Dependency Management
* [Travis](https://travis-ci.com/) - Continuous Integration

## Tests

Tests are executed after each commit with Travis.

## Authors

The two students in charge of development are :
* [feLlx](https://github.com/feLlx)
* [aliliKM](https://github.com/aliliKM)

The teacher in charge of the course and the project :
* [emerite-neou](https://github.com/emerite-neou)

## License

This project is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License) with their permission.
