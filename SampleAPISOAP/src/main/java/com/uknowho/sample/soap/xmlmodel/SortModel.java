
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sortModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sortModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sortModel", propOrder = {
    "descend",
    "field"
})
public class SortModel
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(defaultValue = "true")
    protected boolean descend;
    @XmlElement(required = true)
    protected String field;

    /**
     * Gets the value of the descend property.
     * 
     */
    public boolean isDescend() {
        return descend;
    }

    /**
     * Sets the value of the descend property.
     * 
     */
    public void setDescend(boolean value) {
        this.descend = value;
    }

    /**
     * Gets the value of the field property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

}
