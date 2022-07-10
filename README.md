Como jogar ?

Ja tem dois usuarios cadastrado Johnny e Joao a senha é 123123 mas se quiser fazer outro usuario e senha é so usar o endpoint 
POST - /users

Depois logue na aplicacao e pegue o token com endpoint POST - /authenticate/sign-in

Crie um jogo no endpoint POST /game

E para cards de filmes use o endpoint GET - game/{gameId}/round

Para efetuar uma jogada use o endpoint POST - game/{gameId}/round

Quando quiser para de dar palpite use o endpoint POST - game/1/endGame

Para acessar o rank so usar o endpoint POST - /rank



