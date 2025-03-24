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

## Conclusão  

Esses plugins e configurações ajudam a otimizar o ambiente de desenvolvimento para projetos em Java e Spring Boot no VSCode, proporcionando uma experiência mais fluida e produtiva.  