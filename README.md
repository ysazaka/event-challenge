# Event Finder

Aplicativo com dados falsos criado a fim de botar em prática o clean code, a arquitetura MVVM, etc.

"O Event Finder é um app que busca divulgar eventos os quais os usuários podem se informar, e então se cadastrarem para poder participar destes eventos"

Para que seja possível visualizar o código de uma forma mais estruturada, e também possa ser instalado o app em um dispositivo/emulador Android, 
é necessário baixar o <a href="https://developer.android.com/studio">Android Studio</a>

##

Este projeto contém as seguintes características:
<ul>
  <li>Desenvolvido 100% em Kotlin</li>
  <li>Arquitetura MVVM: utilizado por conta da sua fácil manutenção, fácil testabilidade, e ter a camada lógica fracamante acoplada da camada de apresentação</li>
  <li>Teste unitários</li>
  <li>Clean Code</li>
</ul>

Bibliotecas utilizadas:
<ul>
  <li>Retrofit: conexão com os endpoints</li>
  <li>ViewModel e Livedata: poder aplicar o MVVM, e lidar melhor com os dados</li>
  <li>Koin: injeção de dependências, que além de estruturar bem o código, ajuda nos testes unitários</li>
  <li>Glide: forma rápida e eficiente de apresentar e criar cache de imagens</li>
  <li>Mockk: criação de mocks para utilizar nos testes unitários</li>
</ul>
