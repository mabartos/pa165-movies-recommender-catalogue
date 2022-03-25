# PA165 Theta Project

*PA165 course at MUNI Spring 2022.*

# Instructions

### Milestone 1

- To **compile project and run entity and DAO tests** use: `mvn clean install`
    - Make sure your Java is set to `version 17`

# About Project

- **Name**: Movie Recommender Catalogue
- **Technologies**: Java 17, Spring, Hibernate, Maven, (...?)
- **Developers**:
    - Maxim Svistunov @xsvistun
    - Martin Bartoš @xbartos5
    - Daniel Puchala @xpuchal1
    - Petr Slezar - _Project Leader_ @xslezar
- **Assigment**:
    - The web application is a catalogue of movies of different genres. Each movie has a title, description, film director, and list of actors, and image(s) from the main playbill. Each movie is categorized in one or more genres. Only the administrator can create, remove and update the list of movies. Users can rate the movies according to different criteria (e.g how novel are the ideas of the movie, their final score, etc…). The main feature of the system is that users can pick one movie and get the list of similar movies and / or movies that were liked the most by other users watching the same movie (no need of complex algorithms, some simple recommendation is enough!).


# Project Description

Movie Recommender is a system for movies evidence and exploration, review writing and getting recommendations for films.

## Roles

System has two authorization roles - **Basic User** and **Administrator**.

- Basic user can browse movies in the database. Write and read reviews. Keep a collection of favorite movies and get recommendations for movies.
- Administrator has all possibilities of basic user, but can also add and edit movies as well as persons of film industry.

## Entities

- **DomainEntity** - abstract entity that is extended by every entity, provides access to `code-generated ID` (UUID) and
  audit attributes such as created and updated
- **Movie** - entity representing a movie in the system's database with its genres
- **Person** - entity representing a person of movie industry. In association with movie either a director or an actor
- **Team** - entity representing a composite review for a movie written by a user
- **User** - entity representing a user of the system as described in the previous section

# Diagrams

![Class Diagram](https://gitlab.fi.muni.cz/xslezar/pa165-movies-recommender-catalogue/-/raw/readme/documentation/MovieRecommenderEntity.jpg)
![Use Case Diagram](https://gitlab.fi.muni.cz/xslezar/pa165-movies-recommender-catalogue/-/raw/readme/documentation/movieRecommenderUseCase.png)