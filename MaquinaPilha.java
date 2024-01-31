import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
class MaquinaPilha {
    public static void main(String[] args) throws Exception {
        BufferedReader arquivo;
        arquivo = new BufferedReader(new FileReader(args[0]));
        System.out.println(Calcular(arquivo));
    }
    private static String Calcular(BufferedReader arquivo) throws IOException {
        Stack<Integer> pilhaArquivo = new Stack<>();
        LerArquivo(arquivo, pilhaArquivo);

        while (!pilhaArquivo.isEmpty()) {
            int result = pilhaArquivo.pop();
            return("Resultado: " + result);
        }
        return "";
    }

    private static void LerArquivo(BufferedReader arquivo, Stack<Integer> pilhaArquivo) throws IOException {
        String op;
        while ((op = arquivo.readLine()) != null) {
            Operacao(op, pilhaArquivo);
        }
    }

    private static void Operacao(String op, Stack<Integer> pilhaArquivo) {
        String[] operandos = op.split(" ");

        if (operandos.length > 0) {
            String operacao = operandos[0];

            switch (operacao) {
                case "PUSH":
                    int operando1 = Integer.parseInt(operandos[1]);
                    pilhaArquivo.push(operando1);
                    break;
                case "DIV":
                    CalculaResultado(pilhaArquivo, (a, b) -> b / a);
                    break;
                case "SUB":
                    CalculaResultado(pilhaArquivo, (a, b) -> b - a);
                    break;
                case "MULT":
                    CalculaResultado(pilhaArquivo, (a, b) -> a * b);
                    break;
                case "SUM":
                    CalculaResultado(pilhaArquivo, (a, b) -> a + b);
                    break;
                case "PRINT":
                    break;
                default:
                    System.out.println("Operacao desconhecida: " + operacao);
            }
        }
    }

    private static void CalculaResultado(Stack<Integer> pilhaArquivo, Operacao op) {
        if (pilhaArquivo.size() >= 2) {
            int a = pilhaArquivo.pop();
            int b = pilhaArquivo.pop();
            int result = op.apply(a, b);
            pilhaArquivo.push(result);
        } else {
            System.out.println("Erro: operacao requer pelo menos dois valores na pilha.");
        }
    }

    interface Operacao {
        int apply(int a, int b);
    }
}