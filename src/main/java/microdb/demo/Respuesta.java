package microdb.demo;


public class Respuesta {

    private String resultado;
    private String error;
    
    public String getResultado(){
        return resultado;
    }
    public void setResultado(String resultado){
        this.resultado= resultado;
    }
    public String getError(){
        return error;
    }
    public void setError(String error){
        this.error= error;
    }  
}