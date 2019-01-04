@IGticket @sale @mobile
Feature: Pms ig sale

  Background: 
    Given player Authenticated via "PMS"

  @gameSale
  Scenario Outline: User performs sale of ig games
    Given User performs sale of <gameNumber> and finishes the game

    Examples: 
      | gameNumber | 
      |        210 | 
      |        211 | 
     