# Dominando Git

## Índice
1. [Introdução](#introdução)
2. [Commits](#commits)
3. [Rebase](#rebase)
4. [Desfazendo Commits](#desfazendo-commits)
5. [Puxando Commits Específicos](#puxando-commits-específicos)
6. [Editando Mensagens de Commits](#editando-mensagens-de-commits)
7. [Melhores Práticas](#melhores-práticas)

## Introdução
Este documento visa fornecer uma visão abrangente sobre o uso do Git, desde operações básicas até técnicas avançadas, incluindo melhores práticas para manter um histórico de commits limpo e organizado.

## Commits
### Criando um Commit
```bash
git add <arquivo>
git commit -m "Mensagem do commit"
```

### Padrão de Mensagem de Commit
- **feat**: Uma nova funcionalidade
- **fix**: Correção de bugs
- **docs**: Alterações na documentação
- **style**: Formatação, falta de ponto e vírgula, etc.
- **refactor**: Mudança de código que não corrige um bug nem adiciona uma funcionalidade
- **test**: Adicionando testes faltantes ou corrigindo testes existentes
- **chore**: Atualizando tarefas de build, pacotes, etc.

## Rebase
### Rebase Interativo
```bash
git rebase -i <commit>
```

### Rebase de uma Branch
```bash
git checkout <branch>
git rebase <outra-branch>
```

## Desfazendo Commits
### Sem Perder Alterações
```bash
git reset --soft HEAD~1
```

### Perdendo Alterações
```bash
git reset --hard HEAD~1
```

## Puxando Commits Específicos
```bash
git cherry-pick <commit>
```

## Editando Mensagens de Commits
### Último Commit
```bash
git commit --amend -m "Nova mensagem do commit"
```

### Commit Específico
```bash
git rebase -i <commit>
# Altere 'pick' para 'reword' no commit desejado e salve
```

## Melhores Práticas
- **Commits Pequenos**: Faça commits pequenos e frequentes.
- **Mensagens Claras**: Use mensagens de commit claras e descritivas.
- **Branches**: Use branches para desenvolver novas funcionalidades e corrigir bugs.
- **Revisão de Código**: Sempre revise o código antes de fazer merge.
- **Histórico Limpo**: Use rebase para manter um histórico de commits limpo e linear.
