package microdb.demo;

public class RespuestaCall {
    private int a;
    private int b;
    private int resultado;
    private String error = "Nada";

    public String getError(){
        return error;
    }
    public void setError(String error){
        this.error = error;
    }

    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
    public int getResultado() {
        return resultado;
    }
    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

}
