import { Cliente } from "../clientes/cliente";

export class PrestacaoServico {
    id?: number;
    descricao?: string;
    cliente: Cliente = new Cliente;
    valor?: number;
    dataServico?: Date;
}