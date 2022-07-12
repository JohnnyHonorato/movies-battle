Como jogar ?

Ja tem dois usuarios cadastrado Johnny e Joao a senha é 123123 

Caso queria usar outro usuario use o endpoint POST - /users

Depois logue na aplicacao e pegue o token com endpoint POST - /authenticate/sign-in

Crie um jogo no endpoint POST /game

Para comecar a jogar use o endpoint GET - game/{gameId}/round

Para efetuar uma jogada use o endpoint POST - game/{gameId}/round

Para finalizar o jogo use o endpoint POST - game/1/endGame

Para acessar o rank use o endpoint POST - /rank



