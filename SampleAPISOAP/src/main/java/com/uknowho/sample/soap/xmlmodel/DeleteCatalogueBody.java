
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteCatalogueBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteCatalogueBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="catalogueID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *           &lt;element name="catalogueIDList" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteCatalogueBody", propOrder = {
    "catalogueID",
    "catalogueIDList"
})
public class DeleteCatalogueBody
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected Integer catalogueID;
    @XmlElement(type = Integer.class)
    protected List<Integer> catalogueIDList;

    /**
     * Gets the value of the catalogueID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCatalogueID() {
        return catalogueID;
    }

    /**
     * Sets the value of the catalogueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCatalogueID(Integer value) {
        this.catalogueID = value;
    }

    /**
     * Gets the value of the catalogueIDList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catalogueIDList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogueIDList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getCatalogueIDList() {
        if (catalogueIDList == null) {
            catalogueIDList = new ArrayList<Integer>();
        }
        return this.catalogueIDList;
    }

}
