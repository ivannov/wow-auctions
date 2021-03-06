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
@NamedQueries({
      @NamedQuery(name = "AuctionFile.exists",
                  query = "SELECT COUNT(af) FROM AuctionFile af " +
                          "WHERE af.url = :url AND af.lastModified = :lastModified"),
      @NamedQuery(name = "AuctionFile.findByRealmAndFileStatus",
                  query = "SELECT af FROM AuctionFile af " +
                          "WHERE af.realm.id = :id AND af.fileStatus = :fileStatus ORDER BY af.id"),
})
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
    private FileStatus fileStatus;

    @ManyToOne
    private Realm realm;
}
