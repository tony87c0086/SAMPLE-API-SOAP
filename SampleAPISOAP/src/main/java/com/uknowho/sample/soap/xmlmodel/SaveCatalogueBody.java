
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveCatalogueBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="saveCatalogueBody">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="master" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;choice>
 *           &lt;element name="catalogue" type="{http://www.uknowho.com/xmlmodel/catalogue}catalogueModel"/>
 *           &lt;element name="catalogueList" type="{http://www.uknowho.com/xmlmodel/catalogue}catalogueModel" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "saveCatalogueBody", propOrder = {
    "master",
    "catalogue",
    "catalogueList"
})
public class SaveCatalogueBody
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(defaultValue = "false")
    protected boolean master;
    protected CatalogueModel catalogue;
    protected List<CatalogueModel> catalogueList;

    /**
     * Gets the value of the master property.
     * 
     */
    public boolean isMaster() {
        return master;
    }

    /**
     * Sets the value of the master property.
     * 
     */
    public void setMaster(boolean value) {
        this.master = value;
    }

    /**
     * Gets the value of the catalogue property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogueModel }
     *     
     */
    public CatalogueModel getCatalogue() {
        return catalogue;
    }

    /**
     * Sets the value of the catalogue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogueModel }
     *     
     */
    public void setCatalogue(CatalogueModel value) {
        this.catalogue = value;
    }

    /**
     * Gets the value of the catalogueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catalogueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogueModel }
     * 
     * 
     */
    public List<CatalogueModel> getCatalogueList() {
        if (catalogueList == null) {
            catalogueList = new ArrayList<CatalogueModel>();
        }
        return this.catalogueList;
    }

}
