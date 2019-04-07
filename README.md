# Spring_part4
This is a simple Microservice Application
  App which uses Third Party API of Ice and Fire to find fellows of entered
  characters of the same house who are not dead.Yet.
 
  How to get specified data:
 
  GET + /characters  - returns all data stored in database(paginated)
 
  GET + /characters/{id} - returns one character with specified id
 
  POST + /characters/{name} - returns id of stored entry in database if character is found by API
  
  Characters from houses are chosen randomly.

  Relationship between characters is solely based on API's information
  By dafault, relationship is stated as 'fellows'. Most frequently, it remains as such.
