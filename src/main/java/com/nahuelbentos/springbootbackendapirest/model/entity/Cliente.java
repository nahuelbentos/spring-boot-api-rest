package com.nahuelbentos.springbootbackendapirest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Size(min = 4, max = 12)
    @Column(nullable = false)
    private  String nombre;

    private  String apellido;

    @Email
    @Column(nullable = false, unique = true)
    private  String email;

    @NotNull
    @Column( name = "create_at")
    @Temporal( TemporalType.DATE )
    private Date createAt;

    private String foto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @JsonIgnoreProperties(value = {"cliente","hibernateLazyInitializer", "handler"} , allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }
    //    @PrePersist
//    public void prePersist(){
//        setCreateAt( new Date() );
//    }

}
