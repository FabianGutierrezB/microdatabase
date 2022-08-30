package microdb.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class Celular {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(unique = true)
  private Integer numero;

  private String Marca;

  @OneToOne(mappedBy = "user")
  private User user;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getMarca() {
    return Marca;
  }

  public void setMarca(String marca) {
    Marca = marca;
  }

  // public User getUser() {
  // return user;
  // }

  // public void setUser(User user) {
  // this.user = user;
  // }
}