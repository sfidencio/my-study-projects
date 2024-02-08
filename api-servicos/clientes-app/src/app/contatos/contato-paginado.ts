
//"strictPropertyInitialization": false, no arquivo tsconfig.json

import { Contato } from "./contato";

//nao obriga inicializar variavel
export class ContatoPaginado {
  content: Contato[];
  pageable: [];
  totalPages: number;
  totalElements: number;
  size: number;
  sort: [];
  number: number;
}