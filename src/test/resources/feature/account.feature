Feature: Account End Point
  Background:
    * url 'http://localhost:8766'
    * header Accept = 'application/json'

  Scenario: Testing valid GET generate token
    Given path 'token/generate-token'
    And  request {email:'yelouardi@sqli.com',password:'123456'}
    When method POST
    Then status 200
    And def token = response


  Scenario: Testing OK reponse GET My Account
    Given  path 'token/my-account'
    When method GET
    Then status 200

  Scenario: Testing OK reponse Post SignUP
    Given  path 'users/my-signup'
    And request { accountMailAdresse:'oooo@lll.com',password:'123456',accountFirstName:'test',accountLastName:'test',accountRole:'ADMIN'}
    When method POST
    Then status 401

  Scenario: Testing KO reponse Post SignUP
    Given  path 'users/my-signup'
    And request { accountMailAdresse:'oooo@lll.com',password:'123456',accountFirstName:'test',accountLastName:'test',accountRole:'ADMIN'}
    When method POST
    Then status 401


  Scenario: Testing KO reponse Post SignUP
    Given  path 'users/my-signup'
    And request { accountMailAdresse:'oooo@lll.com',password:'123456',accountFirstName:'test',accountLastName:'test',accountRole:'poeooeee'}
    When method POST
    Then status 401

  Scenario: Testing valid KO generate token
    Given path 'token/generate-token'
    And request {email:'yelouardi@sqli.com',password:'1234dddddd56'}
    When method POST
    Then status 404