
//"strictPropertyInitialization": false, no arquivo tsconfig.json
//nao obriga inicializar variavel
  export class Contato {
    id: string = "";
    nome: string = "";
    email: string = "";
    favorito: boolean = false;
    foto!: Blob;

    /*constructor(nome: string, email: string) {
        this.nome = nome;
        this.email = email;
    }*/

}