# Projeto-ScriptStruct
Projeto de Software, que objetiva a criação de um aplicativo apropriado para escritores e entusiastas por literatura, permitindo que o usuário possa gerir suas narrativas.

O Projeto consegue rodar, contanto que você tenha instalado a linguagem Java e a biblioteca Java Swing, qualquer IDE de Java que atenda a esses requisitos deve funcionar já!!

# Progresso das Funcionalidades do Projeto
Dentre as Funcionalidades prometidas, em desenvolvimento, estão:

(100%) Criação de pastas e arquivos, para permitir o usuário organizar suas histórias

(30%) Formatação de Texto, permitindo que o usuário revise o texto no próprio aplicativo

(0%) Exportação da História, no formato pdf, permitindo o compartilhamento da obra após sua conclusão

(20%) Configuração de Texto, customize o texto para deixar a leitura mais confortável, como fundo claro/escuro

(100%) Criação de Notas, com título e descrição, para escrever ideias, sem estarem vinculadas a um projeto

(100%) Estrutura Assíncrona, o aplicativo não dependerá de Internet

Funcionalidades (Extras)

(0%) Criar templates para facilitar o processo de escrita do Usuário com algumas sugestões

(0%) Lixeira, para a exclusão de histórias, e possível recuperação

(0%) Status de História, permite que o usuário possa definir a prioridade de seus projetos

#Guia de Usuário - Usando o Aplicativo

No total, o aplicativo tem somente 4 Tipos de Telas que o usuário terá acesso:
1. Uma tela para guardar todos os livros criados lhe permitindo renomear ou mesmo deletar o livro junto com todo o conteúdo nele guardado.
2. Uma tela mostrando a organização interna de um livro em específico, lhe dando as opções de criar Pastas, e Arquivos para essas pastas, respectivamente chamados aqui de Categorias e Capítulos.
3. Uma tela com Editor de Texto embutido, feito para permitir os usuários escreverem os Capítulos de suas narrativas, salvando manualmente seu progresso
4. Uma tela que guarda uma anotações, ideias que estão desconectadas do conceito de Livro e sua hierarquia, assumindo o formato de Lista de tarefas que permite o usuário adicionar uma descrição para a tarefa, marcar e desmarcar as ideias já postas em prática, etc.

O Fluxo do Aplicativo segue o seguinte padrão:
Ao rodar o aplicativo, o usuário vê a Tela 1. que no rodapé, possui um botão "Criar Livro" que lhe permitirá gerar o projeto, e no cabeçalho, há um botão que o levará até a Tela 4., que possui um botão simular no seu rodapé, voltado para Criar Notas, e um votão no cabeçalho que lhe permite voltar para a Tela 1.
Quando você dá um Nome ao seu Livro, você gerará um botão com o nome do livro na Tela 1, ao clicar nesse botão você é direcionado para a Tela 2., onde o usuário pode criar uma nova Pasta no botão do Rodapé, gerando um container que possui um simples botão de "+Capítulo" que gera um botão com o nome do Arquivo dentro do container
Ao clicar no botão do Arquivo, você enfim é direcionado para a Tela 3, onde há uma larga área de texto onde você pode escrever sua narrativa, e no rodapé diversas opções para lhe auxiliar: um contador de palavras, um botão para salvar o arquivo, outro para aumentar o tamanho da fonte, assim como o tipo da fonte.
Na Tela 3. há um botão de Voltar no cabeçalho, que lhe permite ir para a Tela 2. onde o arquivo está, que também possui um botão idêntico, que se clicado o levará até a Tela 3, concluindo assim o fluxo expositório de nosso aplicativo.
