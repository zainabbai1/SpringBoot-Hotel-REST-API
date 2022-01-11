package com.example.hotelAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rooms")

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)

public class Room
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    private Integer roomNumber;

//    @NotBlank
    private Integer floor;

//    @NotBlank
    private Integer people;

//    @NotBlank
    private Integer price;

//    @NotBlank
    private Boolean is_available;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Integer getRoomNumber()
    {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() 
    {
        return floor;
    }

    public void setFloor(Integer floor) 
    {
        this.floor = floor;
    }

    public Integer getPeople()
    {
        return people;
    }

    public void setPeople(Integer people) 
    {
        this.people = people;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price) 
    {
        this.price = price;
    }

    public Boolean getIsAvailable() 
    {
        return is_available;
    }

    public void setIsAvailable(Boolean available)
    {
        is_available = available;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }
}