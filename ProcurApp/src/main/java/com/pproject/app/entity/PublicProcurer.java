package com.pproject.app.entity;

import javax.persistence.Entity;

@Entity
public class PublicProcurer extends BusinessEntity {

    PublicProcurerType publicProcurerType;

    public PublicProcurer(int ico, String name, String street, String zipCode, String email, String phoneNumber, PublicProcurerType publicProcurerType) {
        super(ico, name, street, zipCode, email, phoneNumber);
        this.publicProcurerType = publicProcurerType;
    }


    public PublicProcurer(){}
}
