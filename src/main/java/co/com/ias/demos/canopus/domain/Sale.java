package co.com.ias.demos.canopus.domain;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.function.Predicate;

@Document(collection="sales")
public class Sale {
    @Id
    private ObjectId id;
    @DBRef
    private Store store;
    private Date saleDate;
    private Date deliveryDate;
    
   public Predicate<Sale> isSameStore = otherStore -> {
       return this.getId().equals(otherStore.getId());
   };

    public Sale(ObjectId id, Store store, Date saleDate, Date deliveryDate) {
        this.id = checkNotNull(id, "id can't be null");
        this.store = checkNotNull(store, "store can't be null");
        this.saleDate = checkNotNull(saleDate, "saleDate can't be null");
        this.deliveryDate = checkNotNull(deliveryDate, "deliveryDate can't be null");
    }

    public ObjectId getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", location=" + store +
                ", saleDate=" + saleDate +
                ", deliveryDate=" + deliveryDate + 
                '}';
    }
}