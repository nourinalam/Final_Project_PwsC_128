/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.uaspws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "uaspws")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uaspws.findAll", query = "SELECT u FROM Uaspws u"),
    @NamedQuery(name = "Uaspws.findById", query = "SELECT u FROM Uaspws u WHERE u.id = :id"),
    @NamedQuery(name = "Uaspws.findByName", query = "SELECT u FROM Uaspws u WHERE u.name = :name"),
    @NamedQuery(name = "Uaspws.findByNik", query = "SELECT u FROM Uaspws u WHERE u.nik = :nik"),
    @NamedQuery(name = "Uaspws.findByAddress", query = "SELECT u FROM Uaspws u WHERE u.address = :address")})
public class Uaspws implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "nik")
    private String nik;
    @Column(name = "address")
    private String address;
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    public Uaspws() {
    }

    public Uaspws(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uaspws)) {
            return false;
        }
        Uaspws other = (Uaspws) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalproject.uaspws.Uaspws[ id=" + id + " ]";
    }
    
}
