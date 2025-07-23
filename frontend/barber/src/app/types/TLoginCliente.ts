import { TCliente } from "./TCliente";

export type TLoginClienteResponse = {
    message: string;
    token: string;
    clienteInfo: string;
}