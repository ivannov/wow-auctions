package com.radcortez.wow.auctions.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Roberto Cortez
 */
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuctionFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auctionFileId")
    @SequenceGenerator(name = "auctionFileId",
                       sequenceName = "AUCTIONFILE_ID_SEQ",
                       initialValue = 1,
                       allocationSize = 1)
    private Long id;
    private String url;
    private Long lastModified;
    private String fileName;
    private boolean downloaded;
    private boolean loaded;

    @ManyToOne
    private Realm realm;

    public AuctionFile() {
        this.fileName = "auctions." + lastModified + ".json";
    }
}
