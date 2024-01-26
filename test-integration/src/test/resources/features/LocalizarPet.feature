#language: pt

@LocalizarPet
Funcionalidade: Buscar dados da localizacao de um pet atraves de seu dispositivo rastreador

  @Sucesso
  Cenario: Pet localizado com sucesso
    Dado que eu receba as informacoes do pet pelo seu dispositivo
    Entao a API deve retornar a posição completa do pet

  @Falha
  Cenario: Que eu nao consiga a localizacao do pet
    Dado que eu receba as informacoes do pet
    Entao a API nao consiga retornar a localizacao

