
# Dominando o Git

### Comandos git para associar o repositório local ao repositório remoto
```bash
git remote add origin URL_DO_REPOSITORIO
git branch -M main
git push -u origin main
```
### Comandos git para atualizar o repositório local com o repositório remoto
```bash
git pull origin main
```
### Comandos git para atualizar o repositório remoto com o repositório local
```bash
git add .
git commit -m "Mensagem do commit"
git push origin main
```
### Comandos git para criar uma nova branch
```bash
git checkout -b nome_da_branch
```
### Comandos git para listar as branches
```bash
git branch
```
### Comandos git para mudar de branch
```bash
git checkout nome_da_branch
```
### Comandos git para deletar uma branch
```bash
git branch -d nome_da_branch
```
### Comandos git para deletar uma branch remota
```bash
git push origin --delete nome_da_branch
```