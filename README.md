# GitHubApp

Este projeto é um projeto destinado a sand-box com diversos testes tanto de arquitetura de aplicativo mobile (Android/Flutter) quanto de práticas técnicas.

## O que ele contempla? 
- Projeto multimodular escalável.
- Projeto com feature de exemplo (Top Java Repository).
- Projeto com Clean Arc + MVVM + Repository Pattern (Top Java Repository).
- Exemplo de testes unitários em todas as camadas do Clean (Top Java Repository).
- Exemplo de tratativas de acessibilidade (Top Java Repository).
- Boas praticas de uso do Git, (Git Flow, Attomic commits, Semmantic commits).

## O que esta nos planos para ser feito?
- Projeto com feature em Flutter.
- Projeto com feature usando Jetpack-Compose.
- Projeto com feature em repositório apartado.


## Sobre a arquitetura Multimodular.
O projeto foi desenhado para poder escalar seu crescimento para suportar muitos desenvolvedores/squads atuando ao mesmo tempo.

A arquitetura tem alguns módulos chaves e regras que devem ser seguidas para que o seu objetivo seja alcançado com sucesso.
O Módulo **APP** responsável por fazer o start da aplicação deve ter acesso a todas as features, pois ele é responsável por fazer a ponte entre features quando necessário.

Os Módulos de Features, é toda camada central da aplicação, foi desenhado para que cada feature pudesse seguir o padrão que a squad quiser desenvolver, neste exemplo do projeto foi criado uma feature usando MVVM, mas nada impede em ter uma outra feature com MVC, as features não devem fazer referencia uma a outra sempre que precisar de comunicação interna devem fazer via o APP, as features podem e devem usar a camada de Libraries pra compartilhar esforço comum entre times.

Os Módulos de libraries, são tudo que é Cross squad/time que vai além de uma feature aplicando ao escopo do projeto inteiro, no exemplo foi criado duas lib uma somente com código Kotlin (pra poder ser usada nas camadas Domain, quando se utilizar Clean na feature) e uma lib Android pra conter o código da commum da arquitetura do Android, as libraries **NÃO** podem ter dependencias com os módulos Features (caso contrário vai acabar fazendo dependencia entre módulos de forma indireta).

Segue a imagem exemplificando

 ![Arquitetura](https://user-images.githubusercontent.com/151217/134596845-51b14768-f9ea-4476-b685-3f18dd492a2f.jpg)


## Sobre a feature-top-repositories
Tem como objetivo funcional, listar os reposítórios JAVA em ordem dos top rating contendo uma lista infinita e ao clicar deve abrir a listagem de PR do mesmo, segue as imagem dp projeto.

<p align="center">
<img src="https://user-images.githubusercontent.com/151217/134598898-324f823b-2ca8-409e-acbf-9856a27b1029.gif" align="center" width="216"  height="480" />
<img src="https://user-images.githubusercontent.com/151217/134598743-86eb07e0-6f81-407e-858e-16726f33e69f.png" align="center" width="216"  height="480" />
<img src="https://user-images.githubusercontent.com/151217/134598760-04720f4b-7952-483c-8cbb-f683013a30d1.png" align="center" width="216"  height="480" />

</p>


Tem como objetivo técnico, ser um dos módulos (features) exemplo do projeto, ele foi desenvolvido usando Clean Architecture em conjunto com arquitetura MVVM para fazer as tratativas de apresentação e Repository Pattern para as tratativas de acesso aos dados. 

A base para escolha das arquiteturas escolhidas foi o a flexibilidade, ou seja este módulo pode facilmente se adaptar a alterações (mudança de contrato de API, mudança completa de tela, mudança de comportamento de armazenamento de dados), outro ponto importante é que ela é facilmente testada de forma unitaria nas suas principais camadas.

Projeto faz uso do **flow** + **result** e **stateflow** pra fazer as requisições de forma asyncronas, faz uso do **navigation** + **safeArgs** pra fazer a navegação entre as telas, faz uso do **retrofit** pra fazer a requisição a API remota, faz uso do **Koin** como injetor de dependencias, faz uso do **Glide** para o carregamento e cache de imagem remota, utiliziado dependencias unificadas o **buildSrc** pra fazer o gerenciamento das versões.


## Sobre a utilização do Git + Github
Para seguir as boas praticas atuais de desenvolvimento alguns padrões de desenvolvimento foram seguido, toda tarefa executada precisa de uma issue do Github para que a mesma seja feita, todo código mergeado deve seguir via MR mesmo que esteja sendo feito por um unico desenvolvedor.

Os commits sempre que possíveis devem utilizar do  [semmantic/conventional commit ](https://www.conventionalcommits.org/en/v1.0.0/) pra manter uma linha clara e de fácil entendimento do que estava sendo feito através dos commits.

Os commits sempre que possíveis devem seguir o padrão de Atomics Commit (sem deixar coisas pela metade, podendo fazer um revert a um determinado ponto sem quebrar o App).

E foi adotado o Git Flow padrão, evitando long-live branch (toda branch fora as branch base development,release-candidate,master devem ser excluida após sua utilização).

 
