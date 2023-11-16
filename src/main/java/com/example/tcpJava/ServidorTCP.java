package com.example.tcpJava;
import java.net.*;
import java.io.*;

public class ServidorTCP {

    public static void main(String[] args) throws Exception {

        /* cria socket na porta 5000 */
        ServerSocket tomadaServidora = new ServerSocket(5000);
        /* aguarda solicitacao do cliente e aceita conexao */
        System.out.println("Servidor Aguardando Conexões.");
        Socket tomadaCliente = tomadaServidora.accept();

        /* cria dois buffers, um para enviar e outro para receber da conexao com o cliente*/
        PrintWriter bufferSaida = new PrintWriter(tomadaCliente.getOutputStream(), true);
        BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(tomadaCliente.getInputStream()));

        String textoAEnviar, textoRecebido;
        while (true) {
            /* recebe do cliente */
            textoRecebido = bufferEntrada.readLine();
            /* Faz o processamento de acordo com a requisição do cliente */
            textoAEnviar = textoRecebido.toUpperCase();

            if (textoRecebido.equals("BYE")) /* termina se recebeu Bye */ {
                break;
            }
            /* envia para o cliente */
            bufferSaida.println(textoAEnviar);
        }
        /* fecha buffers e socket */
        bufferSaida.close();
        bufferEntrada.close();
        tomadaCliente.close();
        tomadaServidora.close();
    }
}
