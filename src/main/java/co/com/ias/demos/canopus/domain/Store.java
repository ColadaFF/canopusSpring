package co.com.ias.demos.canopus.domain;

import static com.google.common.base.Preconditions.checkNotNull;

public class Store {
    private String name;
    private Location location;
    private String address;

    public Store(String name, Location location, String address) {
        this.name = checkNotNull(name, "name, can't be null");
        this.location = checkNotNull(location, "location, can't be null");
        this.address = checkNotNull(address, "address can't be null");
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                "address='" + address + '\'' +
                ", location=" + location +
                '}';
    }
}
