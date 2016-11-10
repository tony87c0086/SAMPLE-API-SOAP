
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paginationModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paginationModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="perPage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paginationModel", propOrder = {
    "startPage",
    "perPage",
    "firstResult",
    "maxResult"
})
public class PaginationModel
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(defaultValue = "1")
    protected int startPage;
    @XmlElement(defaultValue = "25")
    protected int perPage;
    @XmlElement(defaultValue = "0")
    protected int firstResult;
    @XmlElement(defaultValue = "200")
    protected int maxResult;

    /**
     * Gets the value of the startPage property.
     * 
     */
    public int getStartPage() {
        return startPage;
    }

    /**
     * Sets the value of the startPage property.
     * 
     */
    public void setStartPage(int value) {
        this.startPage = value;
    }

    /**
     * Gets the value of the perPage property.
     * 
     */
    public int getPerPage() {
        return perPage;
    }

    /**
     * Sets the value of the perPage property.
     * 
     */
    public void setPerPage(int value) {
        this.perPage = value;
    }

    /**
     * Gets the value of the firstResult property.
     * 
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * Sets the value of the firstResult property.
     * 
     */
    public void setFirstResult(int value) {
        this.firstResult = value;
    }

    /**
     * Gets the value of the maxResult property.
     * 
     */
    public int getMaxResult() {
        return maxResult;
    }

    /**
     * Sets the value of the maxResult property.
     * 
     */
    public void setMaxResult(int value) {
        this.maxResult = value;
    }

}
