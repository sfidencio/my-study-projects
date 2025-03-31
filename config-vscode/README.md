# Configurações do VSCode

## Principais Plugins  

1. **Java Extension Pack**  
   - Inclui extensões essenciais para desenvolvimento em Java.  
   - Requisitos: Java Development Kit (JDK) instalado.  

2. **Spring Boot Extension Pack**  
   - Conjunto de extensões para facilitar o desenvolvimento com Spring Boot.  
   - Recursos como inicializadores de projeto, suporte a configuração e gerenciamento de dependências.  

3. **Glasslt-VSC de Transparência**  
   - Plugin para dar um efeito de transparência ao fundo do editor.  
   - Configurações personalizáveis para ajustar a opacidade.  

4. **Project Manager for Java**  
   - Ferramenta para gerenciamento de projetos Java dentro do VSCode.  
   - Permite criar, abrir e gerenciar projetos com facilidade.  

5. **Atom One Dark Icon Theme**  
   - Um tema de ícones estiloso que melhora a estética do seu workspace.  
   - Melhora a visualização e organização dos arquivos.  

## Configurações Recomendadas  

- **Java Home**  
  - Configure a variável `JAVA_HOME` para apontar para a instalação do JDK.  

- **Formatação Automática**  
  - Ative a formatação automática de código em `"editor.formatOnSave": true`.  

- **Linting**  
  - Configure ferramentas de linting para garantir qualidade de código.  
  - Exemplo: Spring Boot Linter.  

- **Debugging**  
  - Utilize as ferramentas de debug integradas, configure breakpoints e variáveis de inspeção.  


- **Fonte Fira Code**  
  - Para usar a fonte Fira Code com ligaduras:  
    1. Instale a fonte Fira Code no seu sistema.  
    2. Adicione as seguintes configurações no `settings.json`:  
      ```json  
      "editor.fontFamily": "'Fira Code', 'Courier New', monospace",  
      "editor.fontLigatures": true  
      ```  
  - https://worldofzero.com/posts/enable-font-ligatures-vscode/

- **Atalhos de Teclado**
  - Configure atalhos de teclado para ações frequentes.  
  - Principais atalhos:  
    - `Ctrl + Shift + P`: Comando de busca.  
    - `Ctrl + Shift + N`: Nova janela.  
    - `Ctrl + Shift + E`: Explorador de arquivos.  
    - `Ctrl + Shift + F`: Busca em arquivos.  
    - `Ctrl + Shift + G`: Controle de versão.  
    - `Ctrl + Shift + D`: Depuração.  
    - `Ctrl + Shift + V`: Visualizador de Markdown.  
    - `Ctrl + Shift + X`: Extensões.  
    - `Ctrl + Shift + H`: Substituir em arquivos.  
    - `Ctrl + Shift + J`: Alternar painel de saída.  
    - `Ctrl + Shift + L`: Selecionar todas as ocorrências.  
    - `Ctrl + Shift + M`: Problemas.  
    - `Ctrl + Shift + O`: Ir para símbolo.  
    - `Ctrl + Shift + P`: Comando de busca.  
    - `Ctrl + Shift + R`: Ir para referência.  
    - `Ctrl + Shift + T`: Reabrir editor fechado.  
    - `Ctrl + Shift + U`: Alterar maiúsculas/minúsculas.  
    - `Ctrl + Shift + V`: Visualizador de Markdown.  
    - `Ctrl + Shift + W`: Fechar janela.  
    - `Ctrl + Shift + X`: Extensões.  
    - `Ctrl + Shift + Y`: Alternar painel de saída.  
    - `Ctrl + Shift + Z`: Desfazer.  
    - `Ctrl + Shift + [`: Recuar.  
    - `Ctrl + Shift + ]`: Avançar.  
    - `Ctrl + Shift + \`: Dividir editor.  
    - `Ctrl + Shift + ;`: Adicionar cursor acima.  
    - `Ctrl + Shift + '`: Adicionar cursor abaixo.  
    - `Ctrl + Shift + ,`: Adicionar cursor à direita.  
    - `Ctrl + Shift + .`: Adicionar cursor à esquerda.  
    - `Ctrl + Shift + /`: Comentar linha.  
    - `Ctrl + Shift + \`: Comparar com o arquivo ativo.  
    - `Ctrl + Shift + Enter`: Inserir linha acima.
    - `Ctrl + Shift + Enter`: Inserir linha abaixo. 

- **Configurações autoimport**  
  - Ative a opção de autoimport para importar automaticamente classes e pacotes.  
  - Exemplo: `"java.completion.autoImports": true`.
  - Outro exemplo:
  ```java
    "editor.codeActionsOnSave": {
    "source.organizeImports": true
  }
  ```


## Conclusão  

Esses plugins e configurações ajudam a otimizar o ambiente de desenvolvimento para projetos em Java e Spring Boot no VSCode, proporcionando uma experiência mais fluida e produtiva.  