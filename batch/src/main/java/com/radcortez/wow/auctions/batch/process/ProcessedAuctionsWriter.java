package com.radcortez.wow.auctions.batch.process;

import com.radcortez.wow.auctions.entity.AuctionItemStatistics;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ivan St. Ivanov
 */
@Named
public class ProcessedAuctionsWriter extends AbstractItemWriter {
    @PersistenceContext
    protected EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public void writeItems(List items) throws Exception {
        List<AuctionItemStatistics> statistis = (List<AuctionItemStatistics>) items;
        statistis.forEach(em::persist);
    }
}
