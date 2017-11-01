package envio;

import java.io.Serializable;

/**
 * Created by Jhon on 6/05/16.
 */
public class Usuario implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String name, password,tipo;

    public Usuario(String name, String password, String tipo){
        this.name=name;
        this.password=password;
        this.tipo=tipo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return tipo;
    }





}